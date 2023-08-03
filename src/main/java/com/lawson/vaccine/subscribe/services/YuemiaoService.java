package com.lawson.vaccine.subscribe.services;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.lawson.vaccine.subscribe.dto.UserDTO;
import com.lawson.vaccine.subscribe.dto.UserProxy;
import com.lawson.vaccine.subscribe.entity.RegisterEntity;
import com.lawson.vaccine.subscribe.http.api.WeChatApi;
import com.lawson.vaccine.subscribe.http.vo.wechat.CreateWXVO;
import com.lawson.vaccine.subscribe.http.vo.wechat.WXQrCodeVO;
import com.lawson.vaccine.subscribe.http.vo.wechat.WeChatR;
import com.lawson.vaccine.subscribe.http.vo.yuemiao.CustomerVO;
import com.lawson.vaccine.subscribe.http.vo.yuemiao.VaccineVO;
import com.lawson.vaccine.subscribe.http.wrap.YueMiaoWrap;
import com.lawson.vaccine.subscribe.service.RegisterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class YuemiaoService {

    public static final int NOT_SUBSCRIBE = 0;

    private YueMiaoWrap yueMiaoWrap;

    private RegisterEntityService registerEntityService;

    private WeChatApi weChatApi;

    @Autowired
    public YuemiaoService setYueMiaoWrap(YueMiaoWrap yueMiaoWrap) {
        this.yueMiaoWrap = yueMiaoWrap;
        return this;
    }

    @Autowired
    public YuemiaoService setRegisterEntityService(RegisterEntityService registerEntityService) {
        this.registerEntityService = registerEntityService;
        return this;
    }

    @Autowired
    public YuemiaoService setWeChatApi(WeChatApi weChatApi) {
        this.weChatApi = weChatApi;
        return this;
    }

    public void scanDate() {
        List<RegisterEntity> list = this.registerEntityService.lambdaQuery()
                .eq(RegisterEntity::getSubscribeStatus, NOT_SUBSCRIBE)
                .list();
        Map<Integer, List<Integer>> collect = list.parallelStream()
                .collect(Collectors.groupingBy(RegisterEntity::getCustomerId, Collectors.collectingAndThen(
                        Collectors.toList(), (d) -> d.parallelStream().map(RegisterEntity::getVaccine2).collect(Collectors.toList())
                )));
        UserDTO userDTO = new UserDTO()
                .setCookie("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2OTEwNTEyNzMuNjE2NDY2MywiZXhwIjoxNjkxMDU0ODczLjYxNjQ2NjMsInN1YiI6IllOVy5WSVAiLCJqdGkiOiIyMDIzMDgwMzE2Mjc1MyIsInZhbCI6ImFkZlFBUUlBQUFBRWJtOXVaUnh2Y1hJMWJ6VkhlSFY2TFRSNU5UZHJWRjlMVkVSWlpFMDFkRmhGQUJ4dlZUSTJXSFEyUVMxS1FUTmhcclxuUjBoTlNWaGtSSFU1Y2pkQllXUnpEakV4Tnk0eE56TXVOek11TVRRekFBQUFBQUFBQUE9PSJ9.DQbSzpvmCdgMjWINjMFizCgBytnOTlexcIE_iahChc0")
                .setUserProxy(new UserProxy().setHost("127.0.0.1").setPort("7890"));
        collect.forEach((k, v) -> {
            Optional<CustomerVO> customerVO = this.yueMiaoWrap.customerProduct(userDTO, k);
            customerVO.ifPresent(c -> {
                if (CollUtil.isNotEmpty(c.getList())) {
                    for (VaccineVO vaccineVO : c.getList()) {
                        String vaccineName = vaccineVO.getText();
                        if (vaccineName.contains("九价人乳头")) {
                            this.updateDate(vaccineVO.getDate(), k, 27);
                        } else if (vaccineName.contains("四价人乳头")) {
                            this.updateDate(vaccineVO.getDate(), k, 28);
                        }
                    }
                }
            });
        });
    }

    public void scanSubscribe() {
        LocalDateTime dateTime = LocalDateTime.now().plusMinutes(55);
        List<RegisterEntity> list = this.registerEntityService.lambdaQuery()
                .eq(RegisterEntity::getSubscribeStatus, NOT_SUBSCRIBE)
                .between(RegisterEntity::getNextSubscribeDate, 1, dateTime.toEpochSecond(ZoneOffset.ofHours(8)))
                .list();

        System.out.println(list);
    }

    private void updateDate(String date, Integer customerId, Integer vaccine2) {
        if (StrUtil.isNotEmpty(date)) {
            String startDate = date.substring(0, 11);
            LocalDateTime dateTime = LocalDateTime.parse("2023-" + startDate + ":00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            this.registerEntityService.lambdaUpdate()
                    .eq(RegisterEntity::getCustomerId, customerId)
                    .eq(RegisterEntity::getVaccine2, vaccine2)
                    .update(new RegisterEntity().setNextSubscribeDate(dateTime.toEpochSecond(ZoneOffset.ofHours(8))));
        }
    }

    public String getWxQr(String uuid) {
        Optional<RegisterEntity> registerOpt = this.registerEntityService
                .lambdaQuery()
                .oneOpt();
        if (registerOpt.isPresent()) {
            RegisterEntity register = registerOpt.get();
            if (register.getNextSubscribeDate() < 1) {
                return "";
            }
            WeChatR<CreateWXVO> wx = this.weChatApi.createWX();
            if (Objects.nonNull(wx.getData()) && StrUtil.isNotEmpty(wx.getData().getGuid())) {
                String guid = wx.getData().getGuid();
                this.registerEntityService.updateById(
                        new RegisterEntity()
                                .setRegisterId(register.getRegisterId())
                                .setWxGuid(guid)
                );
                WeChatR<WXQrCodeVO> loginQrcodeWX = this.weChatApi.getLoginQrcodeWX(wx.getData());
                return loginQrcodeWX.getUrl();
            }
        }
        return "";
    }
}
