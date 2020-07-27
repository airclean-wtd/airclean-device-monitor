package com.sailbright.airclean.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

public class DeviceSmplData implements Serializable {

    private BigInteger smplNo;  //采样编号

    private String deviceNo;    //设备编号

    private String deviceTp;    //设备类型

    private int dataTp;         //数据类型

    private String val;         //采样数据

    private String io;          //室内&室外

    private Timestamp smplTm;   //采样时间


    public BigInteger getSmplNo() {
        return smplNo;
    }

    public void setSmplNo(BigInteger smplNo) {
        this.smplNo = smplNo;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getDeviceTp() {
        return deviceTp;
    }

    public void setDeviceTp(String deviceTp) {
        this.deviceTp = deviceTp;
    }

    public int getDataTp() {
        return dataTp;
    }

    public void setDataTp(int dataTp) {
        this.dataTp = dataTp;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getIo() {
        return io;
    }

    public void setIo(String io) {
        this.io = io;
    }

    public Timestamp getSmplTm() {
        return smplTm;
    }

    public void setSmplTm(Timestamp smplTm) {
        this.smplTm = smplTm;
    }

}
