package com.lawson.vaccine.subscribe.http.vo.yuemiao;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.List;

@ApiModel(description = "疫苗大类列表")
public class Vaccine1VO extends YueMiaoR implements Serializable {

    private List<Vaccine1InternalVO> list;

    public List<Vaccine1InternalVO> getList() {
        return list;
    }

    public Vaccine1VO setList(List<Vaccine1InternalVO> list) {
        this.list = list;
        return this;
    }

    public static class Vaccine1InternalVO {

        private String cname;

        private Integer id;

        public String getCname() {
            return cname;
        }

        public Vaccine1InternalVO setCname(String cname) {
            this.cname = cname;
            return this;
        }

        public Integer getId() {
            return id;
        }

        public Vaccine1InternalVO setId(Integer id) {
            this.id = id;
            return this;
        }

        @Override
        public String toString() {
            return "Vaccine1InternalVO{" +
                    "cname='" + cname + '\'' +
                    ", id=" + id +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Vaccine1VO{" +
                "list=" + list +
                '}';
    }
}
