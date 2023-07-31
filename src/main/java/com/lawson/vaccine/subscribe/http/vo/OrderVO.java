package com.lawson.vaccine.subscribe.http.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class OrderVO implements Serializable {

    private String birthday;
    private String tel;
    private Integer sex;
    private String cname;
    private Integer doctype;
    private String idcard;
    private String mxid;
    private String date;
    private String pid;
    @JSONField(name = "Ftime")
    private Integer Ftime;
    private String guid;

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
