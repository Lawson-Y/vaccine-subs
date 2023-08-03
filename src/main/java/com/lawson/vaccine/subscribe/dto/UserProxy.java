package com.lawson.vaccine.subscribe.dto;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@ApiModel(description = "用户代理")
public class UserProxy implements Serializable {

    private String host;

    private String port;

    private String uname;

    private String passwd;

    public String getHost() {
        return host;
    }

    public UserProxy setHost(String host) {
        this.host = host;
        return this;
    }

    public String getPort() {
        return port;
    }

    public UserProxy setPort(String port) {
        this.port = port;
        return this;
    }

    public String getUname() {
        return uname;
    }

    public UserProxy setUname(String uname) {
        this.uname = uname;
        return this;
    }

    public String getPasswd() {
        return passwd;
    }

    public UserProxy setPasswd(String passwd) {
        this.passwd = passwd;
        return this;
    }

    @Override
    public String toString() {
        return "UserProxy{" +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", uname='" + uname + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }
}
