package com.lawson.vaccine.subscribe.http.vo.yuemiao;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel(description = "机构对象")
public class CustomerVO extends YueMiaoR implements Serializable {

    private String addr;
    private String addr2;

    @JSONField(name = "BigPic")
    private String BigPic;

    @ApiModelProperty(name = "机构名")
    private String cname;

    private String distance;

    @JSONField(name = "IdcardLimit")
    private String IdcardLimit;

    private Double lat;

    private Double lng;

    private String notice;

    private List<String> payment;

    private String tel;

    private List<String> tags;

    private Integer sort;

    private Integer id;

    @ApiModelProperty(name = "疫苗列表")
    private List<VaccineVO> list;

    public String getAddr() {
        return addr;
    }

    public CustomerVO setAddr(String addr) {
        this.addr = addr;
        return this;
    }

    public String getAddr2() {
        return addr2;
    }

    public CustomerVO setAddr2(String addr2) {
        this.addr2 = addr2;
        return this;
    }

    public String getBigPic() {
        return BigPic;
    }

    public CustomerVO setBigPic(String bigPic) {
        BigPic = bigPic;
        return this;
    }

    public String getCname() {
        return cname;
    }

    public CustomerVO setCname(String cname) {
        this.cname = cname;
        return this;
    }

    public String getDistance() {
        return distance;
    }

    public CustomerVO setDistance(String distance) {
        this.distance = distance;
        return this;
    }

    public String getIdcardLimit() {
        return IdcardLimit;
    }

    public CustomerVO setIdcardLimit(String idcardLimit) {
        IdcardLimit = idcardLimit;
        return this;
    }

    public Double getLat() {
        return lat;
    }

    public CustomerVO setLat(Double lat) {
        this.lat = lat;
        return this;
    }

    public Double getLng() {
        return lng;
    }

    public CustomerVO setLng(Double lng) {
        this.lng = lng;
        return this;
    }

    public String getNotice() {
        return notice;
    }

    public CustomerVO setNotice(String notice) {
        this.notice = notice;
        return this;
    }

    public List<String> getPayment() {
        return payment;
    }

    public CustomerVO setPayment(List<String> payment) {
        this.payment = payment;
        return this;
    }

    public String getTel() {
        return tel;
    }

    public CustomerVO setTel(String tel) {
        this.tel = tel;
        return this;
    }

    public List<String> getTags() {
        return tags;
    }

    public CustomerVO setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public CustomerVO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public List<VaccineVO> getList() {
        return list;
    }

    public CustomerVO setList(List<VaccineVO> list) {
        this.list = list;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public CustomerVO setId(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "CustomerVO{" +
                "addr='" + addr + '\'' +
                ", addr2='" + addr2 + '\'' +
                ", BigPic='" + BigPic + '\'' +
                ", cname='" + cname + '\'' +
                ", distance='" + distance + '\'' +
                ", IdcardLimit='" + IdcardLimit + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", notice='" + notice + '\'' +
                ", payment=" + payment +
                ", tel='" + tel + '\'' +
                ", tags=" + tags +
                ", sort=" + sort +
                ", id=" + id +
                ", list=" + list +
                '}';
    }
}
