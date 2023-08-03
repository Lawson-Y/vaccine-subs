package com.lawson.vaccine.subscribe.http.vo.yuemiao;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.List;

@ApiModel(description = "疫苗小类列表")
public class Vaccine2VO extends YueMiaoR implements Serializable {

    private List<Vaccine2InternalVO> list;

    public List<Vaccine2InternalVO> getList() {
        return list;
    }

    public Vaccine2VO setList(List<Vaccine2InternalVO> list) {
        this.list = list;
        return this;
    }

    public static class Vaccine2InternalVO {

        private String alias;

        private Integer id;

        public String getAlias() {
            return alias;
        }

        public Vaccine2InternalVO setAlias(String alias) {
            this.alias = alias;
            return this;
        }

        public Integer getId() {
            return id;
        }

        public Vaccine2InternalVO setId(Integer id) {
            this.id = id;
            return this;
        }

        @Override
        public String toString() {
            return "Vaccine2InternalVO{" +
                    "alias='" + alias + '\'' +
                    ", id=" + id +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Vaccine2VO{" +
                "list=" + list +
                '}';
    }
}
