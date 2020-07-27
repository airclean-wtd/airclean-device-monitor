package com.sailbright.airclean.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class Device implements Serializable {

    private String no;//设备编号

    private String tp;//设备类型

    private int sid;//设备地址

    private String ip;//设备MAC

    private String mac;//设备IP

    private int port;//设备端口

    private int st;//设备状态

    private Timestamp mtTm;//维护时间

    private int del;//删除标志



    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getSt() {
        return st;
    }

    public void setSt(int st) {
        this.st = st;
    }

    public Timestamp getMtTm() {
        return mtTm;
    }

    public void setMtTm(Timestamp mtTm) {
        this.mtTm = mtTm;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }
}
