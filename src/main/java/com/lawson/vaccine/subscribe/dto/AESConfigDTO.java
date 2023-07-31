package com.lawson.vaccine.subscribe.dto;

import java.io.Serializable;

public class AESConfigDTO implements Serializable {

    private String key;

    private String iv = "1234567890000000";

    private String mode = "CBC";

    private String padding = "PKCS7Padding";

    public String getKey() {
        return key;
    }

    public AESConfigDTO setKey(String key) {
        this.key = key;
        return this;
    }

    public String getIv() {
        return iv;
    }

    public AESConfigDTO setIv(String iv) {
        this.iv = iv;
        return this;
    }

    public String getMode() {
        return mode;
    }

    public AESConfigDTO setMode(String mode) {
        this.mode = mode;
        return this;
    }

    public String getPadding() {
        return padding;
    }

    public AESConfigDTO setPadding(String padding) {
        this.padding = padding;
        return this;
    }

    @Override
    public String toString() {
        return "AESConfigDTO{" +
                "key='" + key + '\'' +
                ", iv='" + iv + '\'' +
                ", mode='" + mode + '\'' +
                ", padding='" + padding + '\'' +
                '}';
    }
}
