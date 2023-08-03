package com.lawson.vaccine.subscribe.http.vo.wechat;

import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "获取微信小程序授权信息返回对象")
public class WXJSOperateWxDataVO implements Serializable {
    private String baseResponseObject;
    private String jsapiBaseresponseObject;

    @ApiModelProperty(name = "授权信息，base64编码")
    private String data;
    private String scope;
    private String appname;
    private String appiconUrl;
    private String cancelWording;
    private String allowWording;
    private String applyWording;

    public String getBaseResponseObject() {
        return baseResponseObject;
    }

    public WXJSOperateWxDataVO setBaseResponseObject(String baseResponseObject) {
        this.baseResponseObject = baseResponseObject;
        return this;
    }

    public String getJsapiBaseresponseObject() {
        return jsapiBaseresponseObject;
    }

    public WXJSOperateWxDataVO setJsapiBaseresponseObject(String jsapiBaseresponseObject) {
        this.jsapiBaseresponseObject = jsapiBaseresponseObject;
        return this;
    }

    public String getData() {
        return data;
    }

    public WXJSOperateWxDataVO setData(String data) {
        this.data = data;
        return this;
    }

    public String getScope() {
        return scope;
    }

    public WXJSOperateWxDataVO setScope(String scope) {
        this.scope = scope;
        return this;
    }

    public String getAppname() {
        return appname;
    }

    public WXJSOperateWxDataVO setAppname(String appname) {
        this.appname = appname;
        return this;
    }

    public String getAppiconUrl() {
        return appiconUrl;
    }

    public WXJSOperateWxDataVO setAppiconUrl(String appiconUrl) {
        this.appiconUrl = appiconUrl;
        return this;
    }

    public String getCancelWording() {
        return cancelWording;
    }

    public WXJSOperateWxDataVO setCancelWording(String cancelWording) {
        this.cancelWording = cancelWording;
        return this;
    }

    public String getAllowWording() {
        return allowWording;
    }

    public WXJSOperateWxDataVO setAllowWording(String allowWording) {
        this.allowWording = allowWording;
        return this;
    }

    public String getApplyWording() {
        return applyWording;
    }

    public WXJSOperateWxDataVO setApplyWording(String applyWording) {
        this.applyWording = applyWording;
        return this;
    }

    @Override
    public String toString() {
        return "WXJSOperateWxDataVO{" +
                "baseResponseObject='" + baseResponseObject + '\'' +
                ", jsapiBaseresponseObject='" + jsapiBaseresponseObject + '\'' +
                ", data='" + data + '\'' +
                ", scope='" + scope + '\'' +
                ", appname='" + appname + '\'' +
                ", appiconUrl='" + appiconUrl + '\'' +
                ", cancelWording='" + cancelWording + '\'' +
                ", allowWording='" + allowWording + '\'' +
                ", applyWording='" + applyWording + '\'' +
                '}';
    }

    public WXGetUserInfoVO decode() {
        if (StrUtil.isEmpty(data)) {
            return null;
        }
        return JSONObject.parseObject(Base64Decoder.decode(this.data), WXGetUserInfoVO.class);
    }
}
