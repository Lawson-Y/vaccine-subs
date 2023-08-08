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
 * @since 2023-08-06
 */
@TableName("task_order")
@ApiModel(value = "RegisterEntity对象", description = "")
public class RegisterEntity implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("created_at")
    private Integer createdAt;

    @TableField("status")
    private Integer status;

    @TableField("consuming")
    private Integer consuming;

    @TableField("enabled")
    private Integer enabled;

    @TableField("hospital")
    private String hospital;

    @TableField("hospitalName")
    private String hospitalName;

    @TableField("areaid")
    private String areaid;

    @TableField("sub_date")
    private String subDate;

    @TableField("vaccine1")
    private String vaccine1;

    @TableField("vaccine2")
    private String vaccine2;

    @TableField("agent")
    private String agent;

    @TableField("wx_id")
    private String wxId;

    @TableField("url")
    private String url;

    @TableField("uuid")
    private String uuid;

    public Integer getId() {
        return id;
    }

    public RegisterEntity setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public RegisterEntity setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public RegisterEntity setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getConsuming() {
        return consuming;
    }

    public RegisterEntity setConsuming(Integer consuming) {
        this.consuming = consuming;
        return this;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public RegisterEntity setEnabled(Integer enabled) {
        this.enabled = enabled;
        return this;
    }

    public String getHospital() {
        return hospital;
    }

    public RegisterEntity setHospital(String hospital) {
        this.hospital = hospital;
        return this;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public RegisterEntity setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
        return this;
    }

    public String getAreaid() {
        return areaid;
    }

    public RegisterEntity setAreaid(String areaid) {
        this.areaid = areaid;
        return this;
    }

    public String getSubDate() {
        return subDate;
    }

    public RegisterEntity setSubDate(String subDate) {
        this.subDate = subDate;
        return this;
    }

    public String getVaccine1() {
        return vaccine1;
    }

    public RegisterEntity setVaccine1(String vaccine1) {
        this.vaccine1 = vaccine1;
        return this;
    }

    public String getVaccine2() {
        return vaccine2;
    }

    public RegisterEntity setVaccine2(String vaccine2) {
        this.vaccine2 = vaccine2;
        return this;
    }

    public String getAgent() {
        return agent;
    }

    public RegisterEntity setAgent(String agent) {
        this.agent = agent;
        return this;
    }

    public String getWxId() {
        return wxId;
    }

    public RegisterEntity setWxId(String wxId) {
        this.wxId = wxId;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public RegisterEntity setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public RegisterEntity setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    @Override
    public String toString() {
        return "RegisterEntity{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", status=" + status +
                ", consuming=" + consuming +
                ", enabled=" + enabled +
                ", hospital='" + hospital + '\'' +
                ", hospitalName='" + hospitalName + '\'' +
                ", areaid='" + areaid + '\'' +
                ", subDate='" + subDate + '\'' +
                ", vaccine1='" + vaccine1 + '\'' +
                ", vaccine2='" + vaccine2 + '\'' +
                ", agent='" + agent + '\'' +
                ", wxId='" + wxId + '\'' +
                ", url='" + url + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
