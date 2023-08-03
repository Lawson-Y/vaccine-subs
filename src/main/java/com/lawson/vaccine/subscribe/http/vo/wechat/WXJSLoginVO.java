package com.lawson.vaccine.subscribe.http.vo.wechat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "获取微信小程序code返回对象")
public class WXJSLoginVO implements Serializable {
    private String baseResponseObject;
    private String ssapiBaseresponseObject;

    @ApiModelProperty(name = "登录code")
    private String code;
    private String scopeList;
    private String appname;
    private String appiconUrl;
    private String openid;
    private String sessionKey;
    private String sessionTicket;
    private Integer lifespan;
    private String state;
    private String signature;

    public String getBaseResponseObject() {
        return baseResponseObject;
    }

    public WXJSLoginVO setBaseResponseObject(String baseResponseObject) {
        this.baseResponseObject = baseResponseObject;
        return this;
    }

    public String getSsapiBaseresponseObject() {
        return ssapiBaseresponseObject;
    }

    public WXJSLoginVO setSsapiBaseresponseObject(String ssapiBaseresponseObject) {
        this.ssapiBaseresponseObject = ssapiBaseresponseObject;
        return this;
    }

    public String getCode() {
        return code;
    }

    public WXJSLoginVO setCode(String code) {
        this.code = code;
        return this;
    }

    public String getScopeList() {
        return scopeList;
    }

    public WXJSLoginVO setScopeList(String scopeList) {
        this.scopeList = scopeList;
        return this;
    }

    public String getAppname() {
        return appname;
    }

    public WXJSLoginVO setAppname(String appname) {
        this.appname = appname;
        return this;
    }

    public String getAppiconUrl() {
        return appiconUrl;
    }

    public WXJSLoginVO setAppiconUrl(String appiconUrl) {
        this.appiconUrl = appiconUrl;
        return this;
    }

    public String getOpenid() {
        return openid;
    }

    public WXJSLoginVO setOpenid(String openid) {
        this.openid = openid;
        return this;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public WXJSLoginVO setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
        return this;
    }

    public String getSessionTicket() {
        return sessionTicket;
    }

    public WXJSLoginVO setSessionTicket(String sessionTicket) {
        this.sessionTicket = sessionTicket;
        return this;
    }

    public Integer getLifespan() {
        return lifespan;
    }

    public WXJSLoginVO setLifespan(Integer lifespan) {
        this.lifespan = lifespan;
        return this;
    }

    public String getState() {
        return state;
    }

    public WXJSLoginVO setState(String state) {
        this.state = state;
        return this;
    }

    public String getSignature() {
        return signature;
    }

    public WXJSLoginVO setSignature(String signature) {
        this.signature = signature;
        return this;
    }

    @Override
    public String toString() {
        return "WXJSLoginVO{" +
                "baseResponseObject='" + baseResponseObject + '\'' +
                ", ssapiBaseresponseObject='" + ssapiBaseresponseObject + '\'' +
                ", code='" + code + '\'' +
                ", scopeList='" + scopeList + '\'' +
                ", appname='" + appname + '\'' +
                ", appiconUrl='" + appiconUrl + '\'' +
                ", openid='" + openid + '\'' +
                ", sessionKey='" + sessionKey + '\'' +
                ", sessionTicket='" + sessionTicket + '\'' +
                ", lifespan=" + lifespan +
                ", state='" + state + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
}
