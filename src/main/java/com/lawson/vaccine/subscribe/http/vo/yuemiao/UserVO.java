package com.lawson.vaccine.subscribe.http.vo.yuemiao;

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

    @Override
    public String toString() {
        return "UserVO{" +
                "birthday='" + birthday + '\'' +
                ", tel='" + tel + '\'' +
                ", sex=" + sex +
                ", cname='" + cname + '\'' +
                ", doctype=" + doctype +
                ", idcard='" + idcard + '\'' +
                '}';
    }
}
