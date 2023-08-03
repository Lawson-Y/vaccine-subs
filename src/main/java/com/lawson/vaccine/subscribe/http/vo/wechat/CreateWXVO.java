package com.lawson.vaccine.subscribe.http.vo.wechat;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "创建微信实例返回对象")
public class CreateWXVO implements Serializable {

    @ApiModelProperty(name = "微信实例唯一ID")
    @JSONField(name = "Guid")
    private String Guid;

    public String getGuid() {
        return Guid;
    }

    public CreateWXVO setGuid(String guid) {
        Guid = guid;
        return this;
    }

    @Override
    public String toString() {
        return "CreateWXVO{" +
                "Guid='" + Guid + '\'' +
                '}';
    }
}
