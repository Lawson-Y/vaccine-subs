package com.lawson.vaccine.subscribe.http.wrap;

import com.alibaba.fastjson.JSONObject;
import com.dtflys.forest.http.ForestResponse;
import com.lawson.vaccine.subscribe.http.api.YueMiaoApi;
import com.lawson.vaccine.subscribe.http.vo.*;
import com.lawson.vaccine.subscribe.utils.GenericIdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
     * @param userVO
     * @param orderVO
     * @return
     */
    public Boolean postOrder(UserVO userVO, OrderVO orderVO) {
        ForestResponse<String> res = this.yueMiaoApi.orderPost(userVO.encrypt(orderVO), userVO.getCookie(), GenericIdUtils.zfsw());
        if (200 != res.getStatusCode()) {
            return Boolean.FALSE;
        }
        String newJWT = getJWT(res);
        String result = res.getResult();
        log.info("提交订单->user：{}, \n新cookie：{}, \n数据：{}, \n响应：{}", userVO, newJWT, orderVO, result);
        userVO.setCookie(newJWT);
        YueMiaoR yueMiaoR = JSONObject.parseObject(result, YueMiaoR.class);
        return "200".equals(yueMiaoR.getCode());
    }

    /**
     * 获取验证码
     *
     * @param userVO
     * @param mxid
     * @return
     */
    public String getCaptcha(UserVO userVO, String mxid) {
        ForestResponse<String> res = this.yueMiaoApi.getCaptcha(mxid, userVO.getCookie(), GenericIdUtils.zfsw());
        if (200 != res.getStatusCode()) {
            return "";
        }
        String newJWT = getJWT(res);
        String result = res.getResult();
        log.info("获取验证码->user：{}, \n新cookie：{}, \n数据：{}, \n响应：{}", userVO, newJWT, mxid, result);
        userVO.setCookie(newJWT);
        CaptchaVO rData = JSONObject.parseObject(result, CaptchaVO.class);
        return ("200".equals(rData.getCode()) && rData.getIgnore()) ? rData.getData() : "";
    }

    /**
     * 疫苗可预约时间段
     *
     * @param userVO
     * @return
     */
    public Optional<CustomerDetailVO> custSubscribeDateDetail(UserVO userVO, String pid, Integer id, String month) {
        ForestResponse<String> res = this.yueMiaoApi.getCustSubscribeDateAll(pid, id, month, userVO.getCookie(), GenericIdUtils.zfsw());
        if (200 != res.getStatusCode()) {
            return Optional.empty();
        }
        String result = res.getResult();
        log.info("获取疫苗可预约时间段->user：{}, \n响应：{}", userVO, result);
        return Optional.ofNullable(JSONObject.parseObject(result, CustomerDetailVO.class));
    }

    private static String getJWT(ForestResponse<String> res) {
        return res.getHeader("Set-Cookie").getValue().replace("ASP.NET_SessionId=", "");
    }

}
