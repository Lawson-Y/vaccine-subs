package com.lawson.vaccine.subscribe.http.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class CustSubscribeDateVO  extends YueMiaoR implements Serializable {

    @ApiModelProperty(name = "可预约日期列表")
    private List<String> list;
}
