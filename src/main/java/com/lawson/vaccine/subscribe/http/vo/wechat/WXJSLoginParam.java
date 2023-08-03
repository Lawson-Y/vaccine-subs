package com.lawson.vaccine.subscribe.http.vo.wechat;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "获取小程序登录code参数对象")
public class WXJSLoginParam extends CreateWXVO implements Serializable {

    @ApiModelProperty(name = "小程序appid")
    @JSONField(name = "AppId")
    private String AppId;

    public String getAppId() {
        return AppId;
    }

    public WXJSLoginParam setAppId(String appId) {
        AppId = appId;
        return this;
    }

    @Override
    public String toString() {
        return "WXJSLoginParam{" +
                "AppId='" + AppId + '\'' +
                '}';
    }
}
