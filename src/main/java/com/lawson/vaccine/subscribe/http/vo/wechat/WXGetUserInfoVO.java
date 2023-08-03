package com.lawson.vaccine.subscribe.http.vo.wechat;

import cn.hutool.core.codec.Base64Decoder;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@ApiModel(description = "微信授权信息解析对象")
public class WXGetUserInfoVO implements Serializable {

    private String data;
    private String signature;
    private String encryptedData;
    private String iv;
    private String cloud_id;
    private Integer err_no;

    public String getData() {
        return data;
    }

    public WXGetUserInfoVO setData(String data) {
        this.data = data;
        return this;
    }

    public String getSignature() {
        return signature;
    }

    public WXGetUserInfoVO setSignature(String signature) {
        this.signature = signature;
        return this;
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public WXGetUserInfoVO setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
        return this;
    }

    public String getIv() {
        return iv;
    }

    public WXGetUserInfoVO setIv(String iv) {
        this.iv = iv;
        return this;
    }

    public String getCloud_id() {
        return cloud_id;
    }

    public WXGetUserInfoVO setCloud_id(String cloud_id) {
        this.cloud_id = cloud_id;
        return this;
    }

    public Integer getErr_no() {
        return err_no;
    }

    public WXGetUserInfoVO setErr_no(Integer err_no) {
        this.err_no = err_no;
        return this;
    }

    @Override
    public String toString() {
        return "WXGetUserInfoVO{" +
                "data='" + data + '\'' +
                ", signature='" + signature + '\'' +
                ", encryptedData='" + encryptedData + '\'' +
                ", iv='" + iv + '\'' +
                ", cloud_id='" + cloud_id + '\'' +
                ", err_no=" + err_no +
                '}';
    }
}
