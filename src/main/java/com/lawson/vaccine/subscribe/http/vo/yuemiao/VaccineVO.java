package com.lawson.vaccine.subscribe.http.vo.yuemiao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel(description = "疫苗对象")
public class VaccineVO implements Serializable {

    private String text;
    private String price;
    private String descript;
    private String warn;
    private List<String> tags;
    private Integer questionnaireId;
    private String remarks;
    @ApiModelProperty(name = "针次")
    private List<String> NumbersVaccine;

    @ApiModelProperty(name = "预约期限date=07-24 00:00 至 07-31 16:00")
    private String date;

    @ApiModelProperty(name = "按钮name   BtnLable=立即预约")
    private String BtnLable;
    private Boolean enable;

    private String id;

    public String getText() {
        return text;
    }

    public VaccineVO setText(String text) {
        this.text = text;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public VaccineVO setPrice(String price) {
        this.price = price;
        return this;
    }

    public String getDescript() {
        return descript;
    }

    public VaccineVO setDescript(String descript) {
        this.descript = descript;
        return this;
    }

    public String getWarn() {
        return warn;
    }

    public VaccineVO setWarn(String warn) {
        this.warn = warn;
        return this;
    }

    public List<String> getTags() {
        return tags;
    }

    public VaccineVO setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public Integer getQuestionnaireId() {
        return questionnaireId;
    }

    public VaccineVO setQuestionnaireId(Integer questionnaireId) {
        this.questionnaireId = questionnaireId;
        return this;
    }

    public String getRemarks() {
        return remarks;
    }

    public VaccineVO setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public List<String> getNumbersVaccine() {
        return NumbersVaccine;
    }

    public VaccineVO setNumbersVaccine(List<String> numbersVaccine) {
        NumbersVaccine = numbersVaccine;
        return this;
    }

    public String getDate() {
        return date;
    }

    public VaccineVO setDate(String date) {
        this.date = date;
        return this;
    }

    public String getBtnLable() {
        return BtnLable;
    }

    public VaccineVO setBtnLable(String btnLable) {
        BtnLable = btnLable;
        return this;
    }

    public Boolean getEnable() {
        return enable;
    }

    public VaccineVO setEnable(Boolean enable) {
        this.enable = enable;
        return this;
    }

    public String getId() {
        return id;
    }

    public VaccineVO setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "VaccineVO{" +
                "text='" + text + '\'' +
                ", price='" + price + '\'' +
                ", descript='" + descript + '\'' +
                ", warn='" + warn + '\'' +
                ", tags=" + tags +
                ", questionnaireId=" + questionnaireId +
                ", remarks='" + remarks + '\'' +
                ", NumbersVaccine=" + NumbersVaccine +
                ", date='" + date + '\'' +
                ", BtnLable='" + BtnLable + '\'' +
                ", enable=" + enable +
                ", id='" + id + '\'' +
                '}';
    }
}
