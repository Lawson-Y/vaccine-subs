package com.lawson.vaccine.subscribe.http.vo.yuemiao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "验证码对象")
public class CaptchaVO extends YueMiaoR implements Serializable {

    @ApiModelProperty(name = "忽略验证码")
    private Boolean ignore;

    public Boolean getIgnore() {
        return ignore;
    }

    public CaptchaVO setIgnore(Boolean ignore) {
        this.ignore = ignore;
        return this;
    }

    @Override
    public String toString() {
        return "CaptchaVO{" +
                "ignore=" + ignore +
                '}';
    }
}
