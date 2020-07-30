package com.sailbright.airclean.enums;

public enum MB_REG {

    PM25(0, "PM2.5"),
    TEMPERATURE(1, "温度"),
    HUMIDITY(2, "湿度"),
    PM25_OUT(5, "室外PM2.5");

    private int code;
    private String text;

    private MB_REG(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public int getCode() {
        return code;
    }
}