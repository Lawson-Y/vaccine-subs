package com.lawson.vaccine.subscribe.http.vo;

import cn.hutool.crypto.symmetric.AES;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "用户对象VO")
public class UserVO implements Serializable {

    @ApiModelProperty(name = "用户出生年月")
    private String birthday;

    @ApiModelProperty(name = "用户电话")
    private String tel;

    @ApiModelProperty(name = "用户性别：2=女")
    private Integer sex;

    @ApiModelProperty(name = "用户姓名")
    private String cname;

    @ApiModelProperty(name = "用户信息接口返回的doctype")
    private Integer doctype;

    @ApiModelProperty(name = "用户身份证号")
    private String idcard;

    @ApiModelProperty(name = "加密秘钥")
    private String key;

    @ApiModelProperty(name = "加密向量")
    private String iv = "1234567890000000";

    @ApiModelProperty(name = "加密模式")
    private String mode = "CBC";

    @ApiModelProperty(name = "加密填充模式")
    private String padding = "PKCS7Padding";

    @ApiModelProperty(name = "用户cookie")
    private String cookie;

    public String getBirthday() {
        return birthday;
    }

    public UserVO setBirthday(String birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getTel() {
        return tel;
    }

    public UserVO setTel(String tel) {
        this.tel = tel;
        return this;
    }

    public Integer getSex() {
        return sex;
    }

    public UserVO setSex(Integer sex) {
        this.sex = sex;
        return this;
    }

    public String getCname() {
        return cname;
    }

    public UserVO setCname(String cname) {
        this.cname = cname;
        return this;
    }

    public Integer getDoctype() {
        return doctype;
    }

    public UserVO setDoctype(Integer doctype) {
        this.doctype = doctype;
        return this;
    }

    public String getIdcard() {
        return idcard;
    }

    public UserVO setIdcard(String idcard) {
        this.idcard = idcard;
        return this;
    }

    public String getKey() {
        return key;
    }

    public UserVO setKey(String key) {
        this.key = key;
        return this;
    }

    public String getIv() {
        return iv;
    }

    public UserVO setIv(String iv) {
        this.iv = iv;
        return this;
    }

    public String getMode() {
        return mode;
    }

    public UserVO setMode(String mode) {
        this.mode = mode;
        return this;
    }

    public String getPadding() {
        return padding;
    }

    public UserVO setPadding(String padding) {
        this.padding = padding;
        return this;
    }

    public String getCookie() {
        return cookie;
    }

    public UserVO setCookie(String cookie) {
        this.cookie = cookie;
        return this;
    }

    public String encrypt(OrderVO orderVO) {
        AES aes = new AES(this.getMode(), this.getPadding(), this.getKey().getBytes(), this.getIv().getBytes());
        return aes.encryptHex(JSONObject.toJSONString(orderVO));
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "birthday='" + birthday + '\'' +
                ", tel='" + tel + '\'' +
                ", sex=" + sex +
                ", cname='" + cname + '\'' +
                ", doctype=" + doctype +
                ", idcard='" + idcard + '\'' +
                ", key='" + key + '\'' +
                ", iv='" + iv + '\'' +
                ", mode='" + mode + '\'' +
                ", padding='" + padding + '\'' +
                ", cookie='" + cookie + '\'' +
                '}';
    }
}
