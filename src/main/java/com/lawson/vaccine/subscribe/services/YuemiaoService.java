package com.lawson.vaccine.subscribe.services;

import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.lawson.vaccine.subscribe.dto.UserDTO;
import com.lawson.vaccine.subscribe.dto.WxUuidScanDTO;
import com.lawson.vaccine.subscribe.entity.RegisterEntity;
import com.lawson.vaccine.subscribe.http.api.WeChatApi;
import com.lawson.vaccine.subscribe.http.vo.wechat.*;
import com.lawson.vaccine.subscribe.http.vo.yuemiao.*;
import com.lawson.vaccine.subscribe.http.wrap.YueMiaoWrap;
import com.lawson.vaccine.subscribe.service.RegisterEntityService;
import kdl.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.stream.Collectors;

@Service
public class YuemiaoService {

    public static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final Logger log = LoggerFactory.getLogger(YuemiaoService.class);

    public static final int NOT_SUBSCRIBE = 0;

    public static final int WAIT_SUBSCRIBE = 1;

    private YueMiaoWrap yueMiaoWrap;

    private RegisterEntityService registerEntityService;

    private WeChatApi weChatApi;

    private Client clientProxy;

    public static final ConcurrentHashMap<String, VaccineVO> CUSTOMER_VACCINE_CACHE = new ConcurrentHashMap<>();

    public static final ConcurrentHashMap<String, List<CustSubscribeDateVO.CustSubscribeDateDetailVO>> CUSTOMER_VACCINE_DATE_CACHE = new ConcurrentHashMap<>();

    public static final ConcurrentHashMap<String, List<CustomerSubscribeTimeVO>> CUSTOMER_VACCINE_DATE_TIME_CACHE = new ConcurrentHashMap<>();

    public static final ConcurrentHashMap<String, Integer> BLOCK = new ConcurrentHashMap<>();

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private final ConcurrentHashMap<Integer, ScheduledFuture<?>> futureMap = new ConcurrentHashMap<>();

    @Autowired
    public YuemiaoService setClientProxy(Client clientProxy) {
        this.clientProxy = clientProxy;
        return this;
    }

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

    @Resource
    public YuemiaoService setWeChatApi(WeChatApi weChatApi) {
        this.weChatApi = weChatApi;
        return this;
    }

    public YueMiaoWrap getYueMiaoWrap() {
        return yueMiaoWrap;
    }

    public RegisterEntityService getRegisterEntityService() {
        return registerEntityService;
    }

    public WeChatApi getWeChatApi() {
        return weChatApi;
    }

    public Client getClientProxy() {
        return clientProxy;
    }

    public List<UserDTO> getSubUserList() {
        return subUserList;
    }

    public ThreadPoolTaskScheduler getThreadPoolTaskScheduler() {
        return threadPoolTaskScheduler;
    }

    public ConcurrentHashMap<Integer, ScheduledFuture<?>> getFutureMap() {
        return futureMap;
    }

    public Map<Integer, WxUuidScanDTO> getWaitScanWx() {
        return waitScanWx;
    }

    /**
     * 扫描放号日期
     */
    @Scheduled(cron = "0 0/30 * * * ? ")
    public void scanDate() {
        List<RegisterEntity> list = this.registerEntityService.lambdaQuery()
                .eq(RegisterEntity::getStatus, NOT_SUBSCRIBE)
                .eq(RegisterEntity::getEnabled, 1)
                .list();
        Map<Integer, CustomerVO> customerVOMap = new HashMap<>();
        UserDTO userDTO = new UserDTO()
                .setCookie("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2OTEwNTEyNzMuNjE2NDY2MywiZXhwIjoxNjkxMDU0ODczLjYxNjQ2NjMsInN1YiI6IllOVy5WSVAiLCJqdGkiOiIyMDIzMDgwMzE2Mjc1MyIsInZhbCI6ImFkZlFBUUlBQUFBRWJtOXVaUnh2Y1hJMWJ6VkhlSFY2TFRSNU5UZHJWRjlMVkVSWlpFMDFkRmhGQUJ4dlZUSTJXSFEyUVMxS1FUTmhcclxuUjBoTlNWaGtSSFU1Y2pkQllXUnpEakV4Tnk0eE56TXVOek11TVRRekFBQUFBQUFBQUE9PSJ9.DQbSzpvmCdgMjWINjMFizCgBytnOTlexcIE_iahChc0");
        for (RegisterEntity registerEntity : list) {
            String customerIds = registerEntity.getHospital();
            if (StrUtil.isEmpty(customerIds)) {
                continue;
            }
            String[] split = customerIds.split(",");
            StringJoiner subScribeDate = new StringJoiner(",");
            for (String customerIdStr : split) {
                Integer customerId = Integer.valueOf(customerIdStr);
                CustomerVO customerVO = customerVOMap.get(customerId);
                if (Objects.isNull(customerVO)) {
                    Optional<CustomerVO> customerVOOpt = this.yueMiaoWrap.customerProduct(userDTO, customerId);
                    if (customerVOOpt.isEmpty()) {
                        continue;
                    }
                    customerVO = customerVOOpt.get();
                    customerVOMap.put(customerId, customerVO);
                }
                if (CollUtil.isNotEmpty(customerVO.getList())) {
                    for (VaccineVO vaccineVO : customerVO.getList()) {
                        String vaccineName = vaccineVO.getText();
                        if (vaccineName.contains("九价人乳头") && "27".equals(registerEntity.getVaccine2())) {
                            subScribeDate.add(convertDateToEpochSecond(vaccineVO.getDate()));
                        } else if (vaccineName.contains("四价人乳头") && "28".equals(registerEntity.getVaccine2())) {
                            subScribeDate.add(convertDateToEpochSecond(vaccineVO.getDate()));
                        }
                    }
                }
            }
            this.registerEntityService.updateById(new RegisterEntity()
                    .setId(registerEntity.getId())
                    .setSubDate(subScribeDate.toString())
                    .setStatus(WAIT_SUBSCRIBE)
            );
        }
    }

    private static String convertDateToEpochSecond(String date) {
        if (StrUtil.isNotEmpty(date) && !date.equals("暂无")) {
            String startDate = date.substring(0, 11);
            LocalDateTime dateTime = LocalDateTime.parse("2023-" + startDate + ":00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return String.valueOf(dateTime.toEpochSecond(ZoneOffset.ofHours(8)));
        }
        return "";
    }

    @Deprecated
    private List<UserDTO> subUserList = new ArrayList<>();

    /**
     * 扫描可预约订单
     */
//    @Scheduled(cron = "0 27,57 * * * ? ")
//    @Async(value = "myAsync")
    @Deprecated
    public void scanSubscribe() {
        List<RegisterEntity> list = this.registerEntityService.lambdaQuery()
                .eq(RegisterEntity::getStatus, WAIT_SUBSCRIBE)
                .list();
        LocalDateTime dateTime = LocalDateTime.now().plusMinutes(30);
        List<UserDTO> userDTOList = list.parallelStream()
                .filter(d -> StrUtil.isNotEmpty(d.getWxId()))
                .map(d -> {
                    String[] split = d.getSubDate().split(",");
                    String[] customerList = d.getHospital().split(",");
                    for (int i = 0, len = split.length; i < len; i++) {
                        String s = split[i];
                        if (StrUtil.isEmpty(s)) {
                            continue;
                        }
                        LocalDateTime subDate = LocalDateTime.ofEpochSecond(Long.parseLong(s), 0, ZoneOffset.ofHours(8));
                        if (subDate.isBefore(dateTime)) {
                            WXJSLoginParam wxjsLoginParam = new WXJSLoginParam();
                            wxjsLoginParam
                                    .setAppId("wx2c7f0f3c30d99445")
                                    .setGuid(d.getWxId());
                            WeChatR<WXJSLoginVO> jsCodeR = this.weChatApi.WXJSLogin(wxjsLoginParam);
                            if (Objects.nonNull(jsCodeR.getData())) {
                                WeChatR<WXJSOperateWxDataVO> userInfoR = this.weChatApi.WXJSOperateWxData(wxjsLoginParam);
                                WXGetUserInfoVO userInfoJson = JSONObject.parseObject(Base64Decoder.decode(userInfoR.getData().getData()), WXGetUserInfoVO.class);
                                Optional<UserDTO> auth = this.yueMiaoWrap.auth(jsCodeR.getData().getCode(), userInfoJson.getData(), new UserDTO().setUserProxyIp(d.getProxy()));
                                if (auth.isPresent()) {
                                    UserDTO userDTO = auth.get();
                                    Optional<UserVO> user = this.yueMiaoWrap.user(userDTO);
                                    if (user.isPresent()) {
                                        UserVO userVO = user.get();
                                        userDTO
                                                .setUserProxyIp(d.getProxy())
                                                .setVaccine2(Integer.valueOf(d.getVaccine2()))
                                                .setDate(d.getSubDate())
                                                .setCustomerId(d.getHospital())
                                                .setUuid(d.getUuid())
                                                .setId(d.getId())
                                                .setBirthday(userVO.getBirthday())
                                                .setTel(userVO.getTel())
                                                .setSex(userVO.getSex())
                                                .setCname(userVO.getCname())
                                                .setDoctype(userVO.getDoctype())
                                                .setIdcard(userVO.getIdcard())
                                                .setKey(userInfoJson.getSignature().substring(0, 16));
                                        userDTO.createOrder();
                                        return userDTO;
                                    }
                                }
                            }
                        }
                    }
                    return null;
                }).filter(Objects::nonNull).collect(Collectors.toList());
        subUserList = userDTOList;
        log.info("待订阅列表：{}", JSONObject.toJSONString(userDTOList));
    }

    //    @Scheduled(cron = "58 29,59 * * * ? ")
//    @Async(value = "myAsync")
    @Deprecated
    public void submitOrder() {
        if (CollUtil.isEmpty(this.subUserList)) {
            return;
        }
        Vector<UserDTO> vector = new Vector<>();
        this.doSubmitOrder(vector, this.subUserList);
        if (vector.size() > 0) {
            this.subUserList = ListUtil.toList(vector);
            vector = new Vector<>();
            this.doSubmitOrder(vector, this.subUserList);
        }
    }

    @Deprecated
    private void doSubmitOrder(Vector<UserDTO> vector, List<UserDTO> userDTOList) {
        List<UserDTO> collect = userDTOList.parallelStream()
                .map(d -> {
                    int tryCount = 0;
                    do {
                        if (tryCount > 5) {
                            return null;
                        }
                        tryCount++;
                        String[] dateSplit = d.getDate().split(",");
                        String[] customerIdSplit = d.getCustomerId().split(",");
                        for (int i = 0; i < dateSplit.length; i++) {
                            String s = dateSplit[i];
                            if (NumberUtil.isNumber(s) && Long.parseLong(s) > 0) {
                                Integer customerId = Integer.valueOf(customerIdSplit[i]);
                                LocalDateTime subDate = LocalDateTime.ofEpochSecond(Long.parseLong(s), 0, ZoneOffset.ofHours(8));
                                ScheduledFuture<?> scheduledFuture = this.threadPoolTaskScheduler.schedule(new SubmitOrderTask(this, d.getId(), null),
                                        subDate.minusNanos(d.getOffsetMillis() * 1000000).toInstant(ZoneOffset.ofHours(8)));

                                this.futureMap.put(d.getId(), scheduledFuture);

                                d.getOrderVO().setDate(subDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                                Optional<CustomerVO> customerVO = this.yueMiaoWrap.customerProduct(d, customerId);
                                log.info("uuid：{}，查询产品列表：{}", d.getUuid(), JSONObject.toJSONString(customerVO));
                                if (customerVO.isPresent()) {
                                    List<VaccineVO> vaccineVOList = customerVO.get().getList();
                                    if (CollUtil.isEmpty(vaccineVOList)) {
                                        continue;
                                    }
                                    for (VaccineVO vaccineVO : vaccineVOList) {
                                        String vaccineName = vaccineVO.getText();
                                        String pid = vaccineVO.getId();
                                        if (StrUtil.isEmpty(pid) || StrUtil.isEmpty(vaccineName)) {
                                            continue;
                                        }
                                        Optional<CustSubscribeDateVO> subscribeDateVO = Optional.empty();
                                        OrderVO orderVO = d.getOrderVO();
                                        if (vaccineName.contains("九价人乳头") && d.getVaccine2() == 27) {
                                            orderVO.setPid(pid);
                                            subscribeDateVO = this.yueMiaoWrap.custSubscribeDate(d, pid, customerId, orderVO.getDate().substring(0, 7));
                                        } else if (vaccineName.contains("四价人乳头") && d.getVaccine2() == 28) {
                                            orderVO.setPid(pid);
                                            subscribeDateVO = this.yueMiaoWrap.custSubscribeDate(d, pid, customerId, orderVO.getDate().substring(0, 7));
                                        }
                                        log.info("uuid：{}，查询日期列表：{}", d.getUuid(), JSONObject.toJSONString(subscribeDateVO));
                                        if (subscribeDateVO.isEmpty() || CollUtil.isEmpty(subscribeDateVO.get().getList())) {
                                            vector.add(d);
                                            return null;
                                        }
                                        List<CustSubscribeDateVO.CustSubscribeDateDetailVO> dateList = subscribeDateVO.get().getList();
                                        for (CustSubscribeDateVO.CustSubscribeDateDetailVO dateVO : dateList) {
                                            orderVO.setDate(dateVO.getDate());
                                            Optional<CustomerDetailVO> dateDetail = this.yueMiaoWrap.custSubscribeDateDetail(d, pid, customerId, dateVO.getDate());
                                            log.info("uuid：{}，查询时间列表：{}", d.getUuid(), JSONObject.toJSONString(dateDetail));
                                            if (dateDetail.isEmpty() || CollUtil.isEmpty(dateDetail.get().getList())) {
                                                continue;
                                            }
                                            for (CustomerSubscribeTimeVO timeVO : dateDetail.get().getList()) {
                                                if (timeVO.getQty() == 0) {
                                                    continue;
                                                }
                                                orderVO.setMxid(timeVO.getMxid());
                                                String captcha = this.yueMiaoWrap.getCaptcha(d, timeVO.getMxid());
                                                Boolean orderIsOk = this.yueMiaoWrap.postOrder(d, orderVO);
                                                if (orderIsOk) {
                                                    this.registerEntityService.updateById(new RegisterEntity().setId(d.getId()).setStatus(2));
                                                    log.info("uuid：{}，订阅成功：{}", d.getUuid(), d.getCname());
                                                    return d;
                                                }
                                                log.info("uuid：{}，订阅失败：{}", d.getUuid(), d.getCname());
                                            }
                                        }
                                        vector.add(d);
                                        return null;
                                    }
                                }
                            }
                        }
                    } while (LocalTime.now().getMinute() == 30 || LocalTime.now().getMinute() == 0);
                    return null;
                }).filter(Objects::nonNull).collect(Collectors.toList());
        log.info("尝试订阅数：{}，成功数：{}，失败数：{}", userDTOList.size(), collect.size(), vector.size());
    }

    Map<Integer, WxUuidScanDTO> waitScanWx = new ConcurrentHashMap<>();

    @Scheduled(cron = "0/5 * * * * ? ")
    public void scanWx() {
        Iterator<Map.Entry<Integer, WxUuidScanDTO>> iterator = this.waitScanWx.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, WxUuidScanDTO> next = iterator.next();
            WxUuidScanDTO value = next.getValue();
            int scanNum = value.getScanNum();
            if (scanNum > 10) {
                this.waitScanWx.remove(next.getKey());
            }
            LoginQrcodeStatusParam loginStatusParam = new LoginQrcodeStatusParam();
            loginStatusParam.setUuid(value.getUuid()).setGuid(value.getGuid());
            WeChatR<LoginQrcodeStatusVO> wxStatus = this.weChatApi.WXCheckLoginQrcode(loginStatusParam);
            if (Objects.isNull(wxStatus.getData())) {
                continue;
            }
            LoginQrcodeStatusVO data = wxStatus.getData();
            String wxid = data.getWxid();
            if (StrUtil.isEmpty(wxid)) {
                continue;
            }
            String wxnewpass = data.getWxnewpass();
            WXLoginManualParam loginManualParam = new WXLoginManualParam();
            loginManualParam
                    .setUserName(wxid)
                    .setPassword(wxnewpass)
                    .setGuid(value.getGuid());
            log.info("微信登录参数：{}", loginManualParam);
            WeChatR<WXLoginManualVO> wxLoginR = this.weChatApi.WXLoginManual(loginManualParam);
            if (Objects.nonNull(wxLoginR)) {
                log.info("微信：{}登录成功", value.getGuid());
                this.doJsLogin(value.getUserDTO());
                this.waitScanWx.remove(next.getKey());
            }
        }
    }

    /**
     * 获取微信登录二维码
     *
     * @param uuid
     * @return
     */
    public String getWxQr(String uuid) {
        Optional<RegisterEntity> registerOpt = this.registerEntityService
                .lambdaQuery()
                .eq(RegisterEntity::getUuid, uuid)
                .oneOpt();
        if (registerOpt.isPresent()) {
            RegisterEntity register = registerOpt.get();
            if (register.getStatus().equals(2)) {
                return "";
            }
            String[] split = register.getSubDate().split(",");
            String[] hospital = register.getHospital().split(",");
            LocalDateTime now = LocalDateTime.now();
            for (int i = 0; i < split.length; i++) {
                String s = split[i];
                if (StrUtil.isEmpty(s)) {
                    continue;
                }
                long subDateLong = Long.parseLong(s);
                LocalDateTime subDateTime = LocalDateTime.ofEpochSecond(subDateLong, 0, ZoneOffset.ofHours(8));
                long betweenMinutes = ChronoUnit.MINUTES.between(now, subDateTime);
                if (betweenMinutes >= 30) {
                    return "请" + (betweenMinutes - 30) + "后再来登录，预约时间：" + subDateTime.format(dateTimeFormat);
                }
                if (betweenMinutes < 1) {
                    return "";
                }
                String guid = register.getWxId();
                if (StrUtil.isEmpty(guid)) {
                    WeChatR<CreateWXVO> wx = this.weChatApi.createWX();
                    if (Objects.nonNull(wx.getData()) && StrUtil.isNotEmpty(wx.getData().getGuid())) {
                        this.registerEntityService.updateById(
                                new RegisterEntity()
                                        .setId(register.getId())
                                        .setWxId(guid)
                        );
                        guid = wx.getData().getGuid();
                    }
                }
                if (StrUtil.isNotEmpty(guid)) {
                    String proxy = this.getOneProxyIp("重庆");
                    int validTime = this.getProxyIpValidTime(proxy);
                    while (validTime < 2100){
                        proxy = this.getOneProxyIp("重庆");
                        validTime = this.getProxyIpValidTime(proxy);
                    }
                    String[] proxyIpPort = proxy.split(":");
                    SetProxyWXParam proxyWX = new SetProxyWXParam();
                    proxyWX
                            .setGuid(guid)
                            .setAddress(proxyIpPort[0])
                            .setPort(Integer.valueOf(proxyIpPort[1]))
                            .setEnable(true);
                    WeChatR<Void> voidWeChatR = this.weChatApi.setProxyWX(proxyWX);
                    this.registerEntityService.updateById(
                            new RegisterEntity()
                                    .setId(register.getId())
                                    .setProxy(proxy)
                    );
                    WeChatR<WXQrCodeVO> loginQrcodeWX = this.weChatApi.getLoginQrcodeWX(new CreateWXVO().setGuid(guid));
                    UserDTO userDTO = new UserDTO();
                    userDTO
                            .setWxId(register.getWxId())
                            .setUserProxyIp(proxy)
                            .setVaccine2(Integer.valueOf(register.getVaccine2()))
                            .setDate(split[i])
                            .setCustomerId(hospital[i])
                            .setUuid(register.getUuid())
                            .setId(register.getId());
                    this.waitScanWx.put(register.getId(),
                            new WxUuidScanDTO()
                                    .setGuid(guid)
                                    .setUuid(loginQrcodeWX.getData().getUuid())
                                    .setUserDTO(userDTO)
                    );
                    return loginQrcodeWX.getUrl();
                }
            }
        }
        return "";
    }

    public void doJsLogin(UserDTO userDTO) {
        WXJSLoginParam wxjsLoginParam = new WXJSLoginParam();
        wxjsLoginParam
                .setAppId("wx2c7f0f3c30d99445")
                .setGuid(userDTO.getWxId());
        WeChatR<WXJSLoginVO> jsCodeR = this.weChatApi.WXJSLogin(wxjsLoginParam);
        if (Objects.nonNull(jsCodeR.getData())) {
            WeChatR<WXJSOperateWxDataVO> userInfoR = this.weChatApi.WXJSOperateWxData(wxjsLoginParam);
            WXGetUserInfoVO userInfoJson = JSONObject.parseObject(Base64Decoder.decode(userInfoR.getData().getData()), WXGetUserInfoVO.class);
            Optional<UserDTO> authOpt = this.yueMiaoWrap.auth(jsCodeR.getData().getCode(), userInfoJson.getData(), new UserDTO().setUserProxyIp(userDTO.getUserProxyIp()));
            if (authOpt.isPresent()) {
                UserDTO auth = authOpt.get();
                Optional<UserVO> user = this.yueMiaoWrap.user(userDTO);
                if (user.isPresent()) {
                    UserVO userVO = user.get();
                    userDTO
                            .setCookie(auth.getCookie())
                            .setBirthday(userVO.getBirthday())
                            .setTel(userVO.getTel())
                            .setSex(userVO.getSex())
                            .setCname(userVO.getCname())
                            .setDoctype(userVO.getDoctype())
                            .setIdcard(userVO.getIdcard())
                            .setKey(userInfoJson.getSignature().substring(0, 16));
                    userDTO.createOrder();
                    LocalDateTime subDate = LocalDateTime.parse(userDTO.getDate());
                    ScheduledFuture<?> scheduledFuture = this.threadPoolTaskScheduler.schedule(new SubmitOrderTask(this, userDTO.getId(), userDTO),
                            subDate.minusNanos(userDTO.getOffsetMillis() * 1000000).toInstant(ZoneOffset.ofHours(8)));
                    this.futureMap.put(userDTO.getId(), scheduledFuture);
                }
            }
        }
    }

    public String getOneProxyIp(String area) {
        Map<String, Object> params = new HashMap<>();
        params.put("sign_type", "hmacsha1");
        params.put("format", "json");
        params.put("area", area);
        params.put("pt", 2);
        params.put("st", 2100);
        try {
            String[] dps = this.clientProxy.get_dps(1, params);
            return dps[0];
        } catch (Exception e) {
            log.error("获取代理ip失败", e);
            throw new RuntimeException(e);
        }
    }

    public int getProxyIpValidTime(String ip) {
        try {
            Integer time = this.clientProxy.get_dps_valid_time(ip).get(ip);
            if (Objects.nonNull(time)) {
                return time;
            }
            return 0;
        } catch (Exception e) {
            log.error("获取代理ip有效时间失败", e);
            return 0;
        }
    }
}
