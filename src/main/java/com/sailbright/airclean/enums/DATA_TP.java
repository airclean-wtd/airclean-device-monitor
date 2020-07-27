package com.sailbright.airclean.enums;

public enum DATA_TP {

    PM25(0, "PM2.5"),
    TEMPERATURE(1, "温度"),
    HUMIDITY(2, "湿度"),
    CO2(3, "二氧化碳浓度"),
    HCHO(4, "甲醛浓度"),
    TVOC(5, "TVOC");


    private int code;
    private String text;

    private DATA_TP(int code, String text) {
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