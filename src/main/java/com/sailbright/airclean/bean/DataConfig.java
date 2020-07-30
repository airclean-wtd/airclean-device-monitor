package com.sailbright.airclean.bean;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

public class DataConfig implements Serializable {

    private BigInteger cfgNo;

    private String ky;

    private String val;

    private String name;

    private int odr;

    private Timestamp optTm;

    public BigInteger getCfgNo() {
        return cfgNo;
    }

    public void setCfgNo(BigInteger cfgNo) {
        this.cfgNo = cfgNo;
    }

    public String getKy() {
        return ky;
    }

    public void setKy(String ky) {
        this.ky = ky;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOdr() {
        return odr;
    }

    public void setOdr(int odr) {
        this.odr = odr;
    }

    public Timestamp getOptTm() {
        return optTm;
    }

    public void setOptTm(Timestamp optTm) {
        this.optTm = optTm;
    }
}
