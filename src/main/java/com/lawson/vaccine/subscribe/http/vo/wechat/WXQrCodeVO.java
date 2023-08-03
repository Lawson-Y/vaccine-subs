package com.lawson.vaccine.subscribe.http.vo.wechat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


@ApiModel(description = "微信登录二维码返回对象")
public class WXQrCodeVO implements Serializable {

    @ApiModelProperty(name = "uuid")
    private String uuid;

    @ApiModelProperty(name = "base64二维码")
    private String qrcode;

    @ApiModelProperty(name = "有效期")
    private Integer expiredTime;

    @ApiModelProperty(name = "checkTime")
    private Integer checkTime;

    public String getUuid() {
        return uuid;
    }

    public WXQrCodeVO setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getQrcode() {
        return qrcode;
    }

    public WXQrCodeVO setQrcode(String qrcode) {
        this.qrcode = qrcode;
        return this;
    }

    public Integer getExpiredTime() {
        return expiredTime;
    }

    public WXQrCodeVO setExpiredTime(Integer expiredTime) {
        this.expiredTime = expiredTime;
        return this;
    }

    public Integer getCheckTime() {
        return checkTime;
    }

    public WXQrCodeVO setCheckTime(Integer checkTime) {
        this.checkTime = checkTime;
        return this;
    }

    @Override
    public String toString() {
        return "WXQrCodeVO{" +
                "uuid='" + uuid + '\'' +
                ", qrcode='" + qrcode + '\'' +
                ", expiredTime=" + expiredTime +
                ", checkTime=" + checkTime +
                '}';
    }
}
