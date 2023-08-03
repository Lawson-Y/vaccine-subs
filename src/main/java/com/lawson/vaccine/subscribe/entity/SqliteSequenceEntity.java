package com.lawson.vaccine.subscribe.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author Lawson
 * @since 2023-08-02
 */
@TableName("sqlite_sequence")
@ApiModel(value = "SqliteSequenceEntity对象", description = "")
public class SqliteSequenceEntity implements Serializable {

    @TableField("name")
    private String name;

    @TableField("seq")
    private String seq;

    public String getName() {
        return name;
    }

    public SqliteSequenceEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getSeq() {
        return seq;
    }

    public SqliteSequenceEntity setSeq(String seq) {
        this.seq = seq;
        return this;
    }

    @Override
    public String toString() {
        return "SqliteSequenceEntity{" +
                "name='" + name + '\'' +
                ", seq='" + seq + '\'' +
                '}';
    }
}
