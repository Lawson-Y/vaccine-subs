package com.lawson.vaccine.subscribe.dto;

import java.io.Serializable;

public class WxUuidScanDTO implements Serializable {

    private String uuid;

    private int scanNum;

    private String guid;

    public String getUuid() {
        return uuid;
    }

    public WxUuidScanDTO setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public int getScanNum() {
        return scanNum;
    }

    public WxUuidScanDTO setScanNum(int scanNum) {
        this.scanNum = scanNum;
        return this;
    }

    public String getGuid() {
        return guid;
    }

    public WxUuidScanDTO setGuid(String guid) {
        this.guid = guid;
        return this;
    }
}
