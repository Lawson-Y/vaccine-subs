package com.lawson.vaccine.subscribe.services;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.lawson.vaccine.subscribe.dto.UserDTO;
import com.lawson.vaccine.subscribe.entity.RegisterEntity;
import com.lawson.vaccine.subscribe.http.api.WeChatApi;
import com.lawson.vaccine.subscribe.http.vo.yuemiao.*;
import com.lawson.vaccine.subscribe.http.wrap.YueMiaoWrap;
import com.lawson.vaccine.subscribe.service.RegisterEntityService;
import kdl.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

public class SubmitOrderTask implements Runnable {

    Logger log = LoggerFactory.getLogger(SubmitOrderTask.class);

    private YuemiaoService yuemiaoService;

    private boolean isDone = false;

    private final int id;

    private final ConcurrentHashMap<Integer, ScheduledFuture<?>> future;

    private final YueMiaoWrap yueMiaoWrap;

    private final RegisterEntityService registerEntityService;

    private WeChatApi weChatApi;

    private Client clientProxy;

    private final UserDTO userDTO;

    private LocalDateTime startTime;

    public SubmitOrderTask(YuemiaoService yuemiaoService, int id, UserDTO userDTO) {
        this.yuemiaoService = yuemiaoService;
        this.id = id;
        this.yueMiaoWrap = yuemiaoService.getYueMiaoWrap();
        this.future = yuemiaoService.getFutureMap();
        this.registerEntityService = yuemiaoService.getRegisterEntityService();
        this.weChatApi = yuemiaoService.getWeChatApi();
        this.clientProxy = yuemiaoService.getClientProxy();
        this.userDTO = userDTO;
    }

    @Override
    public void run() {
        if (Objects.isNull(this.startTime)) {
            this.startTime = LocalDateTime.now();
        }
        log.info("uuid{}执行时间{}", this.userDTO.getUuid(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
        if (this.isDone) {
            this.future.get(this.id).cancel(true);
            return;
        }
        Integer customerId = Integer.valueOf(this.userDTO.getCustomerId());
        String vaccineCacheKey = customerId + "-" + this.userDTO.getVaccine2();
        VaccineVO vaccineVO = YuemiaoService.CUSTOMER_VACCINE_CACHE.get(vaccineCacheKey);
        if (Objects.isNull(vaccineVO)) {
            if (Objects.nonNull(YuemiaoService.BLOCK.putIfAbsent(vaccineCacheKey, 1))) {
                vaccineVO = YuemiaoService.CUSTOMER_VACCINE_CACHE.get(vaccineCacheKey);
                if (Objects.nonNull(vaccineVO)) {
                    if (this.submitOrder(vaccineVO)) {
                        this.isDone = true;
                        this.future.get(this.id).cancel(true);
                        return;
                    }
                }
                Optional<CustomerVO> customerVO = this.yueMiaoWrap.customerProduct(this.userDTO, customerId);
                log.info("uuid：{}，查询产品列表：{}", this.userDTO.getUuid(), JSONObject.toJSONString(customerVO));
                if (customerVO.isPresent()) {
                    List<VaccineVO> vaccineVOList = customerVO.get().getList();
                    if (CollUtil.isEmpty(vaccineVOList)) {
                        return;
                    }
                    for (VaccineVO vaccine : vaccineVOList) {
                        if (StrUtil.isEmpty(vaccine.getId())) {
                            continue;
                        }
                        vaccineVO = vaccine;
                        if (vaccine.getText().contains("九价人乳头") && this.userDTO.getVaccine2() == 27) {
                            YuemiaoService.CUSTOMER_VACCINE_CACHE.put(customerId + "-27", vaccine);
                        } else if (vaccine.getText().contains("四价人乳头") && this.userDTO.getVaccine2() == 28) {
                            YuemiaoService.CUSTOMER_VACCINE_CACHE.put(customerId + "-28", vaccine);
                        }
                    }
                }
            }
        }
        if (this.submitOrder(vaccineVO)) {
            this.isDone = true;
            this.future.get(this.id).cancel(true);
            return;
        }
        if (ChronoUnit.MINUTES.between(this.startTime, LocalDateTime.now()) >= 1) {
            this.isDone = true;
            this.future.get(this.id).cancel(true);
        }
    }

    private boolean submitOrder(VaccineVO vaccineVO) {
        String pid = vaccineVO.getId();
        if (StrUtil.isEmpty(pid)) {
            return false;
        }
        Integer customerId = Integer.valueOf(this.userDTO.getCustomerId());
        OrderVO orderVO = this.userDTO.getOrderVO();
        String queryMonth = orderVO.getDate().substring(0, 7);
        List<CustSubscribeDateVO.CustSubscribeDateDetailVO> dateList = this.getDateList(customerId, this.userDTO.getVaccine2(), queryMonth, vaccineVO);
        if (CollUtil.isEmpty(dateList)) {
            return false;
        }
        log.info("uuid：{}，获取的日期列表：{}", this.userDTO.getUuid(), JSONObject.toJSONString(dateList));
        for (CustSubscribeDateVO.CustSubscribeDateDetailVO dateVO : dateList) {
            orderVO.setDate(dateVO.getDate());
            List<CustomerSubscribeTimeVO> timerList = this.getTimerList(pid, dateVO);
            log.info("uuid：{}，获取的时间列表：{}", this.userDTO.getUuid(), JSONObject.toJSONString(dateList));
            for (CustomerSubscribeTimeVO timeVO : timerList) {
                if (this.doSubmitOrder(timeVO)) {
                    return true;
                }
            }
        }
        return false;
    }

    private List<CustSubscribeDateVO.CustSubscribeDateDetailVO> getDateList(Integer customerId, Integer vaccine2, String queryMonth, VaccineVO vaccineVO) {
        String cacheDateKey = customerId + vaccine2 + queryMonth;
        List<CustSubscribeDateVO.CustSubscribeDateDetailVO> dateDetailVOList = YuemiaoService.CUSTOMER_VACCINE_DATE_CACHE.get(cacheDateKey);
        if (CollUtil.isEmpty(dateDetailVOList)) {
            if (Objects.nonNull(YuemiaoService.BLOCK.putIfAbsent(cacheDateKey, 1))) {
                dateDetailVOList = YuemiaoService.CUSTOMER_VACCINE_DATE_CACHE.get(cacheDateKey);
                if (CollUtil.isNotEmpty(dateDetailVOList)) {
                    return dateDetailVOList;
                }
                Optional<CustSubscribeDateVO> subscribeDateVO = this.yueMiaoWrap.custSubscribeDate(this.userDTO, vaccineVO.getId(), customerId, queryMonth);
                log.info("uuid：{}，查询日期列表：{}", this.userDTO.getUuid(), JSONObject.toJSONString(subscribeDateVO));
                if (subscribeDateVO.isPresent() && CollUtil.isNotEmpty(subscribeDateVO.get().getList())) {
                    YuemiaoService.CUSTOMER_VACCINE_DATE_CACHE.put(cacheDateKey, subscribeDateVO.get().getList());
                    return subscribeDateVO.get().getList();
                } else {
                    YuemiaoService.BLOCK.remove(cacheDateKey);
                }
            } else {
                dateDetailVOList = YuemiaoService.CUSTOMER_VACCINE_DATE_CACHE.get(cacheDateKey);
            }
        }
        return dateDetailVOList;
    }

    private List<CustomerSubscribeTimeVO> getTimerList(String pid, CustSubscribeDateVO.CustSubscribeDateDetailVO dateVO) {
        String cacheDateKey = this.userDTO.getCustomerId() + this.userDTO.getVaccine2() + dateVO.getDate();
        List<CustomerSubscribeTimeVO> timeVOList = YuemiaoService.CUSTOMER_VACCINE_DATE_TIME_CACHE.get(cacheDateKey);
        if (CollUtil.isEmpty(timeVOList)) {
            if (Objects.nonNull(YuemiaoService.BLOCK.putIfAbsent(cacheDateKey, 1))) {
                timeVOList = YuemiaoService.CUSTOMER_VACCINE_DATE_TIME_CACHE.get(cacheDateKey);
                if (CollUtil.isNotEmpty(timeVOList)) {
                    return timeVOList;
                }
                Optional<CustomerDetailVO> dateDetail = this.yueMiaoWrap.custSubscribeDateDetail(this.userDTO, pid, Integer.valueOf(this.userDTO.getCustomerId()), dateVO.getDate());
                log.info("uuid：{}，查询时间列表：{}", this.userDTO.getUuid(), JSONObject.toJSONString(dateDetail));
                if (dateDetail.isPresent() && CollUtil.isNotEmpty(dateDetail.get().getList())) {
                    YuemiaoService.CUSTOMER_VACCINE_DATE_TIME_CACHE.put(cacheDateKey, dateDetail.get().getList());
                    return dateDetail.get().getList();
                } else {
                    YuemiaoService.BLOCK.remove(cacheDateKey);
                }
            } else {
                timeVOList = YuemiaoService.CUSTOMER_VACCINE_DATE_TIME_CACHE.get(cacheDateKey);
            }
        }
        return timeVOList;
    }

    private boolean doSubmitOrder(CustomerSubscribeTimeVO timeVO) {
        OrderVO orderVO = this.userDTO.getOrderVO();
        orderVO.setMxid(timeVO.getMxid());
        String captcha = this.yueMiaoWrap.getCaptcha(this.userDTO, timeVO.getMxid());
        Boolean orderIsOk = this.yueMiaoWrap.postOrder(this.userDTO, orderVO);
        if (orderIsOk) {
            this.registerEntityService.updateById(new RegisterEntity().setId(this.userDTO.getId()).setStatus(2));
            log.info("uuid：{}，订阅成功：{}", this.userDTO.getUuid(), this.userDTO.getCname());
            return true;
        }
        log.info("uuid：{}，订阅失败：{}", this.userDTO.getUuid(), this.userDTO.getCname());
        return false;
    }
}
