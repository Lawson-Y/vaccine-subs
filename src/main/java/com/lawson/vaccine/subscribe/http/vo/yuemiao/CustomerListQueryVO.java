package com.lawson.vaccine.subscribe.http.vo.yuemiao;

import com.dtflys.forest.annotation.JSONQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@ApiModel(description = "获取结构列表查询条件对象")
public class CustomerListQueryVO implements Serializable {

    @ApiModelProperty(name = "城市名称：[\"湖南省\",\"长沙市\",\"\"]")
    private String city;

    @ApiModelProperty(name = "维度：28.19409")
    private Double lat;

    @ApiModelProperty(name = "经度：112.982279")
    private Double lng;

    @ApiModelProperty(name = "id：0")
    private Integer id = 0;

    @ApiModelProperty(name = "城市代码：430100")
    private Integer cityCode = 0;

    @ApiModelProperty(name = "产品：25")
    private Integer product;

    public String getCity() {
        return city;
    }

    public CustomerListQueryVO setCity(String city) {
        this.city = city;
        return this;
    }

    public Double getLat() {
        return lat;
    }

    public CustomerListQueryVO setLat(Double lat) {
        this.lat = lat;
        return this;
    }

    public Double getLng() {
        return lng;
    }

    public CustomerListQueryVO setLng(Double lng) {
        this.lng = lng;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public CustomerListQueryVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public CustomerListQueryVO setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
        return this;
    }

    public Integer getProduct() {
        return product;
    }

    public CustomerListQueryVO setProduct(Integer product) {
        this.product = product;
        return this;
    }

    @Override
    public String toString() {
        return "CustomerListQueryVO{" +
                "city=" + city +
                ", lat=" + lat +
                ", lng=" + lng +
                ", id=" + id +
                ", cityCode=" + cityCode +
                ", product=" + product +
                '}';
    }
}
