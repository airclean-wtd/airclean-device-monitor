package com.sailbright.airclean.enums;

public enum SMPL_MTHD {

    API("API"),
    MB("MODBUS");

    private String code;

    private SMPL_MTHD(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}