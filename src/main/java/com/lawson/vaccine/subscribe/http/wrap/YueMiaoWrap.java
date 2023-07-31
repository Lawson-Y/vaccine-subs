package com.lawson.vaccine.subscribe.http.wrap;

import com.alibaba.fastjson.JSONObject;
import com.lawson.vaccine.subscribe.http.api.YueMiaoApi;
import com.lawson.vaccine.subscribe.http.vo.YueMiaoR;
import com.lawson.vaccine.subscribe.utils.GenericIdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class YueMiaoWrap {

    private static final Logger log = LoggerFactory.getLogger(YueMiaoWrap.class);

    private YueMiaoApi yueMiaoApi;

    @Resource
    public YueMiaoWrap setYueMiaoApi(YueMiaoApi yueMiaoApi) {
        this.yueMiaoApi = yueMiaoApi;
        return this;
    }

    public Boolean postOrder(String cookie, String data) {
        String orderPostResult = this.yueMiaoApi.orderPost(data, cookie, GenericIdUtils.zfsw());
        log.info("提交订单->cookie：{}, 数据：{}, 响应：{}", cookie, data, orderPostResult);
        YueMiaoR yueMiaoR = JSONObject.parseObject(orderPostResult, YueMiaoR.class);
        return "200".equals(yueMiaoR.getCode());
    }
}
