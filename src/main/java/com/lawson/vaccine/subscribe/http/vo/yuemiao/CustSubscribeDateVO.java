package com.lawson.vaccine.subscribe.http.vo.yuemiao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@ApiModel(description = "预约日期列表响应对象")
public class CustSubscribeDateVO extends YueMiaoR implements Serializable {

    @ApiModelProperty(name = "可预约日期列表")
    private List<CustSubscribeDateDetailVO> list;

    @ApiModel(description = "预约日期对象")
    public static class CustSubscribeDateDetailVO implements Serializable {

        @ApiModelProperty(name = "日期")
        private String date;

        @ApiModelProperty(name = "开放状态")
        private Boolean enable;

        public String getDate() {
            return date;
        }

        public CustSubscribeDateDetailVO setDate(String date) {
            this.date = date;
            return this;
        }

        public Boolean getEnable() {
            return enable;
        }

        public CustSubscribeDateDetailVO setEnable(Boolean enable) {
            this.enable = enable;
            return this;
        }

        @Override
        public String toString() {
            return "CustSubscribeDateDetailVO{" +
                    "date=" + date +
                    ", enable=" + enable +
                    '}';
        }
    }

    public List<CustSubscribeDateDetailVO> getList() {
        return list;
    }

    public CustSubscribeDateVO setList(List<CustSubscribeDateDetailVO> list) {
        this.list = list;
        return this;
    }

    @Override
    public String toString() {
        return "CustSubscribeDateVO{" +
                "list=" + list +
                '}';
    }
}
