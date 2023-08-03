package com.lawson.vaccine.subscribe.http.vo.yuemiao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel(description = "机构列表对象")
public class CustomerListVO extends YueMiaoR implements Serializable {

    @ApiModelProperty(name = "机构对象")
    private List<CustomerVO> list;

    public List<CustomerVO> getList() {
        return list;
    }

    public CustomerListVO setList(List<CustomerVO> list) {
        this.list = list;
        return this;
    }

    @Override
    public String toString() {
        return "CustomerListVO{" +
                "list=" + list +
                '}';
    }
}
