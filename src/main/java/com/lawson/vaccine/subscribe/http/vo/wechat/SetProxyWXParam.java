package com.lawson.vaccine.subscribe.http.vo.wechat;

import com.alibaba.fastjson.annotation.JSONField;
import com.dtflys.forest.converter.json.FastjsonEncoder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "设置微信代理参数对象")
public class SetProxyWXParam implements Serializable {

    @ApiModelProperty(name = "微信实例唯一ID")
    @JSONField(name = "Guid")
    private String Guid;

    @ApiModelProperty(name = "开关")
    @JSONField(name = "Enable")
    private Boolean Enable;

    @ApiModelProperty(name = "IP地址")
    @JSONField(name = "Address")
    private String Address;

    @ApiModelProperty(name = "端口")
    @JSONField(name = "Port")
    private Integer Port;

    @ApiModelProperty(name = "用户名")
    @JSONField(name = "UserName")
    private String UserName;

    @ApiModelProperty(name = "密码")
    @JSONField(name = "Password")
    private String Password;

    public String getGuid() {
        return Guid;
    }

    public SetProxyWXParam setGuid(String guid) {
        Guid = guid;
        return this;
    }

    public Boolean getEnable() {
        return Enable;
    }

    public SetProxyWXParam setEnable(Boolean enable) {
        Enable = enable;
        return this;
    }

    public String getAddress() {
        return Address;
    }

    public SetProxyWXParam setAddress(String address) {
        Address = address;
        return this;
    }

    public Integer getPort() {
        return Port;
    }

    public SetProxyWXParam setPort(Integer port) {
        Port = port;
        return this;
    }

    public String getUserName() {
        return UserName;
    }

    public SetProxyWXParam setUserName(String userName) {
        UserName = userName;
        return this;
    }

    public String getPassword() {
        return Password;
    }

    public SetProxyWXParam setPassword(String password) {
        Password = password;
        return this;
    }

    @Override
    public String toString() {
        return "SetProxyWXParam{" +
                "Guid='" + Guid + '\'' +
                ", Enable=" + Enable +
                ", Address='" + Address + '\'' +
                ", Port=" + Port +
                ", UserName='" + UserName + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
