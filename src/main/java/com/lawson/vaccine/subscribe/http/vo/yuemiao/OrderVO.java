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

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Integer getDoctype() {
        return doctype;
    }

    public void setDoctype(Integer doctype) {
        this.doctype = doctype;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getMxid() {
        return mxid;
    }

    public void setMxid(String mxid) {
        this.mxid = mxid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Integer getFtime() {
        return Ftime;
    }

    public void setFtime(Integer ftime) {
        Ftime = ftime;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
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
