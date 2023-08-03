package com.lawson.vaccine.subscribe.http.vo.wechat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "微信登录二维码状态返回对象")
public class LoginQrcodeStatusVO implements Serializable {

    private String device;
    private Integer EffectiveTime;
    private String headImgUrl;
    private String nickName;
    private Integer pushLoginUrlexpiredTime;
    private Integer state;
    private Integer t10;
    private String uuid;

    @ApiModelProperty(name = "登录id(最终用这个账号登录)")
    private String wxid;

    @ApiModelProperty(name = "登录密码(最终用这个密码登录)")
    private String wxnewpass;

    public String getDevice() {
        return device;
    }

    public LoginQrcodeStatusVO setDevice(String device) {
        this.device = device;
        return this;
    }

    public Integer getEffectiveTime() {
        return EffectiveTime;
    }

    public LoginQrcodeStatusVO setEffectiveTime(Integer effectiveTime) {
        EffectiveTime = effectiveTime;
        return this;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public LoginQrcodeStatusVO setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
        return this;
    }

    public String getNickName() {
        return nickName;
    }

    public LoginQrcodeStatusVO setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public Integer getPushLoginUrlexpiredTime() {
        return pushLoginUrlexpiredTime;
    }

    public LoginQrcodeStatusVO setPushLoginUrlexpiredTime(Integer pushLoginUrlexpiredTime) {
        this.pushLoginUrlexpiredTime = pushLoginUrlexpiredTime;
        return this;
    }

    public Integer getState() {
        return state;
    }

    public LoginQrcodeStatusVO setState(Integer state) {
        this.state = state;
        return this;
    }

    public Integer getT10() {
        return t10;
    }

    public LoginQrcodeStatusVO setT10(Integer t10) {
        this.t10 = t10;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public LoginQrcodeStatusVO setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getWxid() {
        return wxid;
    }

    public LoginQrcodeStatusVO setWxid(String wxid) {
        this.wxid = wxid;
        return this;
    }

    public String getWxnewpass() {
        return wxnewpass;
    }

    public LoginQrcodeStatusVO setWxnewpass(String wxnewpass) {
        this.wxnewpass = wxnewpass;
        return this;
    }

    @Override
    public String toString() {
        return "LoginQrcodeStatusVO{" +
                "device='" + device + '\'' +
                ", EffectiveTime=" + EffectiveTime +
                ", headImgUrl='" + headImgUrl + '\'' +
                ", nickName='" + nickName + '\'' +
                ", pushLoginUrlexpiredTime=" + pushLoginUrlexpiredTime +
                ", state=" + state +
                ", t10=" + t10 +
                ", uuid='" + uuid + '\'' +
                ", wxid='" + wxid + '\'' +
                ", wxnewpass='" + wxnewpass + '\'' +
                '}';
    }
}
