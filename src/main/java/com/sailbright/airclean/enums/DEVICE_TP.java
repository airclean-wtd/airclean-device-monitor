package com.sailbright.airclean.enums;

public enum DEVICE_TP {

    Q3("Q3"),
    Q4("Q4"),
    X3S("X3S");

    private String code;

    private DEVICE_TP(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}