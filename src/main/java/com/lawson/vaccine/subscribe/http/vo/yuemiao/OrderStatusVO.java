package com.lawson.vaccine.subscribe.http.vo.yuemiao;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "订单状态对象")
public class OrderStatusVO extends YueMiaoR implements Serializable {

    @ApiModelProperty(name = "不知道")
    @JSONField(name = "Fnumber")
    private String Fnumber;

    public String getFnumber() {
        return Fnumber;
    }

    public OrderStatusVO setFnumber(String fnumber) {
        Fnumber = fnumber;
        return this;
    }

    @Override
    public String toString() {
        return "OrderStatusVO{" +
                "Fnumber='" + Fnumber + '\'' +
                '}';
    }
}
