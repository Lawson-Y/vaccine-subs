package com.lawson.vaccine.subscribe.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author Lawson
 * @since 2023-08-02
 */
@TableName("register")
@ApiModel(value = "RegisterEntity对象", description = "")
public class RegisterEntity implements Serializable {

    @TableId(value = "register_id", type = IdType.AUTO)
    private Integer registerId;

    @TableField("member_name")
    private String memberName;

    @TableField("member_phone")
    private String memberPhone;

    @TableField("subscribe_status")
    private Integer subscribeStatus;

    @TableField("vaccine_1")
    private Integer vaccine1;

    @TableField("vaccine_2")
    private Integer vaccine2;

    @TableField("customer_id")
    private Integer customerId;

    @TableField("next_subscribe_date")
    private Long nextSubscribeDate;

    @TableField("wx_guid")
    private String wxGuid;

    public Integer getRegisterId() {
        return registerId;
    }

    public RegisterEntity setRegisterId(Integer registerId) {
        this.registerId = registerId;
        return this;
    }

    public String getMemberName() {
        return memberName;
    }

    public RegisterEntity setMemberName(String memberName) {
        this.memberName = memberName;
        return this;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public RegisterEntity setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
        return this;
    }

    public Integer getSubscribeStatus() {
        return subscribeStatus;
    }

    public RegisterEntity setSubscribeStatus(Integer subscribeStatus) {
        this.subscribeStatus = subscribeStatus;
        return this;
    }

    public Integer getVaccine1() {
        return vaccine1;
    }

    public RegisterEntity setVaccine1(Integer vaccine1) {
        this.vaccine1 = vaccine1;
        return this;
    }

    public Integer getVaccine2() {
        return vaccine2;
    }

    public RegisterEntity setVaccine2(Integer vaccine2) {
        this.vaccine2 = vaccine2;
        return this;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public RegisterEntity setCustomerId(Integer customerId) {
        this.customerId = customerId;
        return this;
    }

    public Long getNextSubscribeDate() {
        return nextSubscribeDate;
    }

    public RegisterEntity setNextSubscribeDate(Long nextSubscribeDate) {
        this.nextSubscribeDate = nextSubscribeDate;
        return this;
    }

    public String getWxGuid() {
        return wxGuid;
    }

    public RegisterEntity setWxGuid(String wxGuid) {
        this.wxGuid = wxGuid;
        return this;
    }

    @Override
    public String toString() {
        return "RegisterEntity{" +
                "registerId=" + registerId +
                ", memberName='" + memberName + '\'' +
                ", memberPhone='" + memberPhone + '\'' +
                ", subscribeStatus=" + subscribeStatus +
                ", vaccine1=" + vaccine1 +
                ", vaccine2=" + vaccine2 +
                ", customerId=" + customerId +
                ", nextSubscribeDate=" + nextSubscribeDate +
                ", wxGuid='" + wxGuid + '\'' +
                '}';
    }
}
