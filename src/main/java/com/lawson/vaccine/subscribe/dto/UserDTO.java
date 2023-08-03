package com.lawson.vaccine.subscribe.dto;

import cn.hutool.crypto.symmetric.AES;
import com.alibaba.fastjson.JSONObject;
import com.lawson.vaccine.subscribe.http.vo.yuemiao.OrderVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "用户对象DTO")
public class UserDTO implements Serializable {

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

    @ApiModelProperty(name = "时间偏移")
    private long offsetMillis = 0;

    @ApiModelProperty(name = "用户代理")
    private UserProxy userProxy;

    public String getBirthday() {
        return birthday;
    }

    public UserDTO setBirthday(String birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getTel() {
        return tel;
    }

    public UserDTO setTel(String tel) {
        this.tel = tel;
        return this;
    }

    public Integer getSex() {
        return sex;
    }

    public UserDTO setSex(Integer sex) {
        this.sex = sex;
        return this;
    }

    public String getCname() {
        return cname;
    }

    public UserDTO setCname(String cname) {
        this.cname = cname;
        return this;
    }

    public Integer getDoctype() {
        return doctype;
    }

    public UserDTO setDoctype(Integer doctype) {
        this.doctype = doctype;
        return this;
    }

    public String getIdcard() {
        return idcard;
    }

    public UserDTO setIdcard(String idcard) {
        this.idcard = idcard;
        return this;
    }

    public String getKey() {
        return key;
    }

    public UserDTO setKey(String key) {
        this.key = key;
        return this;
    }

    public String getIv() {
        return iv;
    }

    public UserDTO setIv(String iv) {
        this.iv = iv;
        return this;
    }

    public String getMode() {
        return mode;
    }

    public UserDTO setMode(String mode) {
        this.mode = mode;
        return this;
    }

    public String getPadding() {
        return padding;
    }

    public UserDTO setPadding(String padding) {
        this.padding = padding;
        return this;
    }

    public String getCookie() {
        return cookie;
    }

    public UserDTO setCookie(String cookie) {
        this.cookie = cookie;
        return this;
    }

    public String encrypt(OrderVO orderVO) {
        AES aes = new AES(this.getMode(), this.getPadding(), this.getKey().getBytes(), this.getIv().getBytes());
        return aes.encryptHex(JSONObject.toJSONString(orderVO));
    }

    public long getOffsetMillis() {
        return offsetMillis;
    }

    public UserDTO setOffsetMillis(long offsetMillis) {
        this.offsetMillis = offsetMillis;
        return this;
    }

    public UserProxy getUserProxy() {
        return userProxy;
    }

    public UserDTO setUserProxy(UserProxy userProxy) {
        this.userProxy = userProxy;
        return this;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
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
                ", offsetMillis=" + offsetMillis +
                ", userProxy=" + userProxy +
                '}';
    }
}
