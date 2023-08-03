package com.lawson.vaccine.subscribe.http.vo.wechat;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "获取微信登录二维码状态参数对象")
public class LoginQrcodeStatusParam extends CreateWXVO implements Serializable {

    @ApiModelProperty(name = "二维码UUID")
    @JSONField(name = "Uuid")
    private String Uuid;

    public String getUuid() {
        return Uuid;
    }

    public LoginQrcodeStatusParam setUuid(String uuid) {
        Uuid = uuid;
        return this;
    }

    @Override
    public String toString() {
        return "LoginQrcodeStatusParam{" +
                "Uuid='" + Uuid + '\'' +
                '}';
    }
}
