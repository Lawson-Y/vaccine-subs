package com.lawson.vaccine.subscribe.http.vo.yuemiao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@ApiModel(description = "机构（customer）详情响应对象")
public class CustomerDetailVO extends YueMiaoR implements Serializable {

    @ApiModelProperty(name = "当前时间戳")
    private Long current;

    @ApiModelProperty(name = "机构名称")
    private String customer;

    @ApiModelProperty(name = "结束预约日期时间")
    private LocalDateTime end;

    @ApiModelProperty(name = "机构id")
    private Integer id;

    @ApiModelProperty(name = "开始预约日期时间")
    private LocalDateTime start;

    @ApiModelProperty(name = "版本")
    private String ver;

    @ApiModelProperty(name = "可预约时间段列表")
    private List<CustomerSubscribeTimeVO> list;

    public Long getCurrent() {
        return current;
    }

    public CustomerDetailVO setCurrent(Long current) {
        this.current = current;
        return this;
    }

    public String getCustomer() {
        return customer;
    }

    public CustomerDetailVO setCustomer(String customer) {
        this.customer = customer;
        return this;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public CustomerDetailVO setEnd(LocalDateTime end) {
        this.end = end;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public CustomerDetailVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public CustomerDetailVO setStart(LocalDateTime start) {
        this.start = start;
        return this;
    }

    public String getVer() {
        return ver;
    }

    public CustomerDetailVO setVer(String ver) {
        this.ver = ver;
        return this;
    }

    public List<CustomerSubscribeTimeVO> getList() {
        return list;
    }

    public CustomerDetailVO setList(List<CustomerSubscribeTimeVO> list) {
        this.list = list;
        return this;
    }

    @Override
    public String toString() {
        return "CustomerDetailVO{" +
                "current=" + current +
                ", customer='" + customer + '\'' +
                ", end=" + end +
                ", id=" + id +
                ", start=" + start +
                ", ver='" + ver + '\'' +
                ", list=" + list +
                '}';
    }
}
