package com.lawson.vaccine.subscribe.http.vo.yuemiao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "约苗统一返回对象")
public class YueMiaoR implements Serializable {

    @ApiModelProperty(name = "响应码：200")
    private String code;

    @ApiModelProperty(name = "响应码：200")
    private String status;

    @ApiModelProperty(name = "响应消息")
    private String msg;

    @ApiModelProperty(name = "响应数据")
    private String data;

    @ApiModelProperty(name = "sessionId")
    private String sessionId;

    private UserVO user;

    public UserVO getUser() {
        return user;
    }

    public YueMiaoR setUser(UserVO user) {
        this.user = user;
        return this;
    }

    public String getCode() {
        return code;
    }

    public YueMiaoR setCode(String code) {
        this.code = code;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public YueMiaoR setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public YueMiaoR setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getData() {
        return data;
    }

    public YueMiaoR setData(String data) {
        this.data = data;
        return this;
    }

    public String getSessionId() {
        return sessionId;
    }

    public YueMiaoR setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    @Override
    public String toString() {
        return "YueMiaoR{" +
                "code='" + code + '\'' +
                ", status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }
}
