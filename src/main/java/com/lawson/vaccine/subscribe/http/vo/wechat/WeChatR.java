package com.lawson.vaccine.subscribe.http.vo.wechat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "微信协议统一返回对象")
public class WeChatR<T> implements Serializable {

    @ApiModelProperty(name = "错误码")
    private String code;

    @ApiModelProperty(name = "消息")
    private String message;

    @ApiModelProperty(name = "相关的链接帮助地址")
    private String url;

    @ApiModelProperty(name = "备注")
    private String remark;

    @ApiModelProperty(name = "备注")
    private String WXJScode;

    @ApiModelProperty(name = "备注")
    private String WxData;

    @ApiModelProperty(name = "标志信息（标识环境信息是否已改变）")
    private Integer flag;

    @ApiModelProperty(name = "数据")
    private T data;

    public String getCode() {
        return code;
    }

    public WeChatR<T> setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public WeChatR<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public WeChatR<T> setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public WeChatR<T> setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public String getWXJScode() {
        return WXJScode;
    }

    public WeChatR<T> setWXJScode(String WXJScode) {
        this.WXJScode = WXJScode;
        return this;
    }

    public String getWxData() {
        return WxData;
    }

    public WeChatR<T> setWxData(String wxData) {
        WxData = wxData;
        return this;
    }

    public Integer getFlag() {
        return flag;
    }

    public WeChatR<T> setFlag(Integer flag) {
        this.flag = flag;
        return this;
    }

    public T getData() {
        return data;
    }

    public WeChatR<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "WeChatR{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", url='" + url + '\'' +
                ", remark='" + remark + '\'' +
                ", WXJScode='" + WXJScode + '\'' +
                ", WxData='" + WxData + '\'' +
                ", flag=" + flag +
                ", data=" + data +
                '}';
    }
}
