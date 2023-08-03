package com.lawson.vaccine.subscribe.http.vo.wechat;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@ApiModel(description = "微信登录参数对象")
public class WXLoginManualParam extends CreateWXVO implements Serializable {

    @JSONField(name = "Channel")
    private Integer Channel = 0;

    @JSONField(name = "UserName")
    private String UserName;

    @JSONField(name = "Password")
    private String Password;

    @JSONField(name = "Slider")
    private Boolean Slider = true;

    public Integer getChannel() {
        return Channel;
    }

    public WXLoginManualParam setChannel(Integer channel) {
        Channel = channel;
        return this;
    }

    public String getUserName() {
        return UserName;
    }

    public WXLoginManualParam setUserName(String userName) {
        UserName = userName;
        return this;
    }

    public String getPassword() {
        return Password;
    }

    public WXLoginManualParam setPassword(String password) {
        Password = password;
        return this;
    }

    public Boolean getSlider() {
        return Slider;
    }

    public WXLoginManualParam setSlider(Boolean slider) {
        Slider = slider;
        return this;
    }

    @Override
    public String toString() {
        return "WXLoginManualParam{" +
                "Channel=" + Channel +
                ", UserName='" + UserName + '\'' +
                ", Password='" + Password + '\'' +
                ", Slider=" + Slider +
                '}';
    }
}
