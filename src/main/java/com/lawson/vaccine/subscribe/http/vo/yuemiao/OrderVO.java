package com.lawson.vaccine.subscribe.http.vo.yuemiao;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "约苗提交订单对象")
public class OrderVO implements Serializable {

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

    @ApiModelProperty(name = "接口返回的mxid")
    private String mxid;

    @ApiModelProperty(name = "预约日期")
    private String date;

    @ApiModelProperty(name = "接口返回的pid")
    private String pid;

    @ApiModelProperty(name = "固定为1")
    @JSONField(name = "Ftime")
    private Integer Ftime = 1;

    @ApiModelProperty(name = "固定为空")
    private String guid = "";

    public String getBirthday() {
        return birthday;
    }

    public OrderVO setBirthday(String birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getTel() {
        return tel;
    }

    public OrderVO setTel(String tel) {
        this.tel = tel;
        return this;
    }

    public Integer getSex() {
        return sex;
    }

    public OrderVO setSex(Integer sex) {
        this.sex = sex;
        return this;
    }

    public String getCname() {
        return cname;
    }

    public OrderVO setCname(String cname) {
        this.cname = cname;
        return this;
    }

    public Integer getDoctype() {
        return doctype;
    }

    public OrderVO setDoctype(Integer doctype) {
        this.doctype = doctype;
        return this;
    }

    public String getIdcard() {
        return idcard;
    }

    public OrderVO setIdcard(String idcard) {
        this.idcard = idcard;
        return this;
    }

    public String getMxid() {
        return mxid;
    }

    public OrderVO setMxid(String mxid) {
        this.mxid = mxid;
        return this;
    }

    public String getDate() {
        return date;
    }

    public OrderVO setDate(String date) {
        this.date = date;
        return this;
    }

    public String getPid() {
        return pid;
    }

    public OrderVO setPid(String pid) {
        this.pid = pid;
        return this;
    }

    public Integer getFtime() {
        return Ftime;
    }

    public OrderVO setFtime(Integer ftime) {
        Ftime = ftime;
        return this;
    }

    public String getGuid() {
        return guid;
    }

    public OrderVO setGuid(String guid) {
        this.guid = guid;
        return this;
    }

    @Override
    public String toString() {
        return "OrderVO{" +
                "birthday='" + birthday + '\'' +
                ", tel='" + tel + '\'' +
                ", sex=" + sex +
                ", cname='" + cname + '\'' +
                ", doctype=" + doctype +
                ", idcard='" + idcard + '\'' +
                ", mxid='" + mxid + '\'' +
                ", date='" + date + '\'' +
                ", pid='" + pid + '\'' +
                ", Ftime=" + Ftime +
                ", guid='" + guid + '\'' +
                '}';
    }
}
