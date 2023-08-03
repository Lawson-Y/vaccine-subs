package com.lawson.vaccine.subscribe.http.vo.wechat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "微信登录返回对象")
public class WXLoginManualVO implements Serializable {

    private Integer Code;
    @ApiModelProperty(name = "用户信息")
    private String Message;
    private String Url;
    private String Ticket;
    private String Server = null;
    private Integer LoginMode;
    private Integer LoginChannel;

    public Integer getCode() {
        return Code;
    }

    public WXLoginManualVO setCode(Integer code) {
        Code = code;
        return this;
    }

    public String getMessage() {
        return Message;
    }

    public WXLoginManualVO setMessage(String message) {
        Message = message;
        return this;
    }

    public String getUrl() {
        return Url;
    }

    public WXLoginManualVO setUrl(String url) {
        Url = url;
        return this;
    }

    public String getTicket() {
        return Ticket;
    }

    public WXLoginManualVO setTicket(String ticket) {
        Ticket = ticket;
        return this;
    }

    public String getServer() {
        return Server;
    }

    public WXLoginManualVO setServer(String server) {
        Server = server;
        return this;
    }

    public Integer getLoginMode() {
        return LoginMode;
    }

    public WXLoginManualVO setLoginMode(Integer loginMode) {
        LoginMode = loginMode;
        return this;
    }

    public Integer getLoginChannel() {
        return LoginChannel;
    }

    public WXLoginManualVO setLoginChannel(Integer loginChannel) {
        LoginChannel = loginChannel;
        return this;
    }

    @Override
    public String toString() {
        return "WXLoginManualVO{" +
                "Code=" + Code +
                ", Message='" + Message + '\'' +
                ", Url='" + Url + '\'' +
                ", Ticket='" + Ticket + '\'' +
                ", Server='" + Server + '\'' +
                ", LoginMode=" + LoginMode +
                ", LoginChannel=" + LoginChannel +
                '}';
    }
}
