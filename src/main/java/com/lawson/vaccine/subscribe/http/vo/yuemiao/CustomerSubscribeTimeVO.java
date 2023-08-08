package com.lawson.vaccine.subscribe.http.vo.yuemiao;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalTime;

@ApiModel(description = "日期可预约时间段对象")
public class CustomerSubscribeTimeVO extends YueMiaoR implements Serializable {

    @ApiModelProperty(name = "机构名称")
    private String customer;

    @ApiModelProperty(name = "机构id")
    private Integer customerid;

    @ApiModelProperty(name = "时间段id")
    private String mxid;

    @ApiModelProperty(name = "剩余数量")
    private Integer qty;

    @ApiModelProperty(name = "接种开始时间")
    @JSONField(name = "StartTime")
    private LocalTime StartTime;

    @ApiModelProperty(name = "接种结束时间")
    @JSONField(name = "EndTime")
    private LocalTime EndTime;

    public String getCustomer() {
        return customer;
    }

    public CustomerSubscribeTimeVO setCustomer(String customer) {
        this.customer = customer;
        return this;
    }

    public Integer getCustomerid() {
        return customerid;
    }

    public CustomerSubscribeTimeVO setCustomerid(Integer customerid) {
        this.customerid = customerid;
        return this;
    }

    public String getMxid() {
        return mxid;
    }

    public CustomerSubscribeTimeVO setMxid(String mxid) {
        this.mxid = mxid;
        return this;
    }

    public Integer getQty() {
        return qty;
    }

    public CustomerSubscribeTimeVO setQty(Integer qty) {
        this.qty = qty;
        return this;
    }

    public LocalTime getStartTime() {
        return StartTime;
    }

    public CustomerSubscribeTimeVO setStartTime(LocalTime startTime) {
        StartTime = startTime;
        return this;
    }

    public LocalTime getEndTime() {
        return EndTime;
    }

    public CustomerSubscribeTimeVO setEndTime(LocalTime endTime) {
        EndTime = endTime;
        return this;
    }

    @Override
    public String toString() {
        return "CustomerSubscribeTimeVO{" +
                "customer='" + customer + '\'' +
                ", customerid=" + customerid +
                ", mxid='" + mxid + '\'' +
                ", qty=" + qty +
                ", StartTime=" + StartTime +
                ", EndTime=" + EndTime +
                '}';
    }
}
