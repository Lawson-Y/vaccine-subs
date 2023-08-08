package com.lawson.vaccine.subscribe.http.wrap;

import com.alibaba.fastjson.JSONObject;
import com.dtflys.forest.http.ForestResponse;
import com.lawson.vaccine.subscribe.dto.UserDTO;
import com.lawson.vaccine.subscribe.http.api.YueMiaoApi;
import com.lawson.vaccine.subscribe.http.vo.yuemiao.*;
import com.lawson.vaccine.subscribe.utils.GenericIdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class YueMiaoWrap {

    private static final Logger log = LoggerFactory.getLogger(YueMiaoWrap.class);

    private YueMiaoApi yueMiaoApi;

    @Resource
    public YueMiaoWrap setYueMiaoApi(YueMiaoApi yueMiaoApi) {
        this.yueMiaoApi = yueMiaoApi;
        return this;
    }

    /**
     * 提交订单
     *
     * @param userDTO
     * @param orderVO
     * @return
     */
    public Boolean postOrder(UserDTO userDTO, OrderVO orderVO) {
        ForestResponse<String> res = this.yueMiaoApi.orderPost(userDTO.encrypt(orderVO), userDTO, GenericIdUtils.zfsw(userDTO.getOffsetMillis()));
        if (200 != res.getStatusCode()) {
            return Boolean.FALSE;
        }
        String newJWT = getJWT(res, userDTO);
        String result = res.getResult();
        log.info("提交订单->user：{}, \n新cookie：{}, \n数据：{}, \n响应：{}", userDTO, newJWT, orderVO, result);
        userDTO.setCookie(newJWT);
        YueMiaoR yueMiaoR = JSONObject.parseObject(result, YueMiaoR.class);
        return "200".equals(yueMiaoR.getStatus());
    }

    /**
     * 获取验证码
     *
     * @param userDTO
     * @param mxid
     * @return
     */
    public String getCaptcha(UserDTO userDTO, String mxid) {
        ForestResponse<String> res = this.yueMiaoApi.getCaptcha(mxid, userDTO, GenericIdUtils.zfsw(userDTO.getOffsetMillis()));
        if (200 != res.getStatusCode()) {
            return "";
        }
        String newJWT = getJWT(res, userDTO);
        String result = res.getResult();
        log.info("获取验证码->user：{}, \n新cookie：{}, \n数据：{}, \n响应：{}", userDTO, newJWT, mxid, result);
        userDTO.setCookie(newJWT);
        CaptchaVO rData = JSONObject.parseObject(result, CaptchaVO.class);
        return ("200".equals(rData.getStatus()) && rData.getIgnore()) ? rData.getData() : "";
    }

    /**
     * 疫苗可预约日期列表
     *
     * @param userDTO
     * @return
     */
    public Optional<CustSubscribeDateVO> custSubscribeDate(UserDTO userDTO, String pid, Integer id, String month) {
        ForestResponse<String> res = this.yueMiaoApi.getCustSubscribeDateAll(pid, id, month, userDTO, GenericIdUtils.zfsw(userDTO.getOffsetMillis()));
        if (200 != res.getStatusCode()) {
            return Optional.empty();
        }
        String result = res.getResult();
        log.info("获取疫苗可预约日期列表->user：{}, \n响应：{}", userDTO, result);
        return Optional.ofNullable(JSONObject.parseObject(result, CustSubscribeDateVO.class));
    }

    /**
     * 疫苗可预约时间段
     *
     * @param userDTO
     * @return
     */
    public Optional<CustomerDetailVO> custSubscribeDateDetail(UserDTO userDTO, String pid, Integer id, String date) {
        ForestResponse<String> res = this.yueMiaoApi.getCustSubscribeDateDetail(pid, id, date, userDTO, GenericIdUtils.zfsw(userDTO.getOffsetMillis()));
        if (200 != res.getStatusCode()) {
            return Optional.empty();
        }
        String result = res.getResult();
        log.info("获取疫苗可预约时间段->user：{}, \n响应：{}", userDTO, result);
        return Optional.ofNullable(JSONObject.parseObject(result, CustomerDetailVO.class));
    }

    /**
     * 获取机构疫苗产品列表
     *
     * @param userDTO
     * @return
     */
    public Optional<CustomerVO> customerProduct(UserDTO userDTO, Integer id) {
        ForestResponse<String> res = this.yueMiaoApi.customerProduct(id, userDTO, GenericIdUtils.zfsw(userDTO.getOffsetMillis()));
        if (200 != res.getStatusCode()) {
            return Optional.empty();
        }
        String result = res.getResult();
        log.info("获取机构疫苗列表->user：{}, \n响应：{}", userDTO, result);
        return Optional.ofNullable(JSONObject.parseObject(result, CustomerVO.class));
    }

    /**
     * 获取用户信息
     *
     * @param userDTO
     * @return
     */
    public Optional<UserVO> user(UserDTO userDTO) {
        ForestResponse<String> res = this.yueMiaoApi.user(userDTO, GenericIdUtils.zfsw(userDTO.getOffsetMillis()));
        if (200 != res.getStatusCode()) {
            return Optional.empty();
        }
        String result = res.getResult();
        log.info("获取用户信息->user：{}, \n响应：{}", userDTO, result);
        return Optional.ofNullable(JSONObject.parseObject(result, YueMiaoR.class).getUser());
    }

    /**
     * 用户认证授权
     *
     * @param rawdata
     * @return
     */
    public Optional<UserDTO> auth(String code, String rawdata) {
        ForestResponse<String> res = this.yueMiaoApi.auth(code, rawdata, GenericIdUtils.zfsw(0));
        if (200 != res.getStatusCode()) {
            return Optional.empty();
        }
        String result = res.getResult();
        log.info("用户认证授权->rawdata：{}, \n响应：{}", rawdata, result);
        YueMiaoR r = JSONObject.parseObject(result, YueMiaoR.class);
        if (!"200".equals(r.getStatus())) {
            return Optional.empty();
        }
        return Optional.of(new UserDTO().setCookie(r.getSessionId()));
    }

    /**
     * 获取机构列表
     *
     * @param queryVO
     * @return
     */
    public List<CustomerVO> customerList(CustomerListQueryVO queryVO) {
        ForestResponse<String> res = this.yueMiaoApi.customerList(queryVO, GenericIdUtils.zfsw(0));
        if (200 != res.getStatusCode()) {
            return new ArrayList<>(1);
        }
        String result = res.getResult();
        log.info("获取机构列表->data：{}, \n响应：{}", queryVO, result);
        CustomerListVO r = JSONObject.parseObject(result, CustomerListVO.class);
        if (!"200".equals(r.getStatus())) {
            return new ArrayList<>(1);
        }
        return r.getList();
    }

    /**
     * 获取疫苗小类列表
     *
     * @param id
     * @return
     */
    public List<Vaccine2VO.Vaccine2InternalVO> getCat2(Integer id) {
        ForestResponse<String> res = this.yueMiaoApi.getCat2(id, GenericIdUtils.zfsw(0));
        if (200 != res.getStatusCode()) {
            return new ArrayList<>(1);
        }
        String result = res.getResult();
        log.info("获取疫苗小类列表->data：{}, \n响应：{}", id, result);
        Vaccine2VO r = JSONObject.parseObject(result, Vaccine2VO.class);
        if (!"200".equals(r.getStatus())) {
            return new ArrayList<>(1);
        }
        return r.getList();
    }

    /**
     * 获取疫苗大类列表
     *
     * @return
     */
    public List<Vaccine1VO.Vaccine1InternalVO> getCat1() {
        ForestResponse<String> res = this.yueMiaoApi.getCat1(GenericIdUtils.zfsw(0));
        if (200 != res.getStatusCode()) {
            return new ArrayList<>(1);
        }
        String result = res.getResult();
        log.info("获取疫苗大类列表->响应：{}", result);
        Vaccine1VO r = JSONObject.parseObject(result, Vaccine1VO.class);
        if (!"200".equals(r.getStatus())) {
            return new ArrayList<>(1);
        }
        return r.getList();
    }

    /**
     * 获取订单状态
     *
     * @param userDTO
     * @return
     */
    public String getOrderStatus(UserDTO userDTO) {
        ForestResponse<String> res = this.yueMiaoApi.getOrderStatus(userDTO, GenericIdUtils.zfsw(userDTO.getOffsetMillis()));
        if (200 != res.getStatusCode()) {
            return null;
        }
        String newJWT = getJWT(res, userDTO);
        String result = res.getResult();
        log.info("获取订单状态->user：{}, \n新cookie：{}, \n响应：{}", userDTO, newJWT, result);
        userDTO.setCookie(newJWT);
        OrderStatusVO r = JSONObject.parseObject(result, OrderStatusVO.class);
        return r.getFnumber();
    }

    /**
     * 获取订单列表
     *
     * @param userDTO
     * @return
     */
    public List<OrderListVO.OrderDetailVO> userSubcribeList(UserDTO userDTO) {
        ForestResponse<String> res = this.yueMiaoApi.userSubcribeList(userDTO, GenericIdUtils.zfsw(userDTO.getOffsetMillis()));
        if (200 != res.getStatusCode()) {
            return null;
        }
        String newJWT = getJWT(res, userDTO);
        String result = res.getResult();
        log.info("获取订单列表->user：{}, \n新cookie：{}, \n响应：{}", userDTO, newJWT, result);
        userDTO.setCookie(newJWT);
        OrderListVO r = JSONObject.parseObject(result, OrderListVO.class);
        return r.getList();
    }

    private static String getJWT(ForestResponse<String> res, UserDTO userDTO) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime date = LocalDateTime.parse(res.getHeaderValue("Date"), DateTimeFormatter.RFC_1123_DATE_TIME)
                .atZone(ZoneId.of("GMT+0")).withZoneSameInstant(ZoneId.of("Asia/Shanghai")).toLocalDateTime();
        log.info("服务器时间：{}", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(date));
        log.info("本地时间：{}", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(now));
        log.info("时差：{}", Duration.between(now, date).toMillis());
        userDTO.setOffsetMillis(Duration.between(now, date).toMillis());
        return res.getHeader("Set-Cookie").getValue().replace("ASP.NET_SessionId=", "");
    }

}
