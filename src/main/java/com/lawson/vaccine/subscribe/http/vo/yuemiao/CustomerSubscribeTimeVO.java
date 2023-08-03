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
}
