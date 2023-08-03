package com.lawson.vaccine.subscribe.http.vo.yuemiao;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel(description = "订单列表对象")
public class OrderListVO extends YueMiaoR implements Serializable {

    private List<OrderDetailVO> list;

    public List<OrderDetailVO> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "OrderListVO{" +
                "list=" + list +
                '}';
    }

    public OrderListVO setList(List<OrderDetailVO> list) {
        this.list = list;
        return this;
    }

    public static class OrderDetailVO implements Serializable {
        @ApiModelProperty(name = "用于查询订单详情的id")
        private String id;
        private String cname;
        private String cdate;
        private String username;

        @ApiModelProperty(name = "不知道")
        @JSONField(name = "Fnumber")
        private String Fnumber;
        @ApiModelProperty(name = "疫苗名称")
        private String product;
        private String VaccineDate;
        @ApiModelProperty(name = "是否取消")
        private Boolean isCancel;
        private Boolean isFinished;

        @ApiModelProperty(name = "不是订单里面提交的mxid")
        private String mxid;
        private String vdate;
        private String EndTime;
        private String StartTime;
        private Boolean finishedBtn;
    }
}
