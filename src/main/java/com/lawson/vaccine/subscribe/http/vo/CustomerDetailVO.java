package com.lawson.vaccine.subscribe.http.vo;

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
}
