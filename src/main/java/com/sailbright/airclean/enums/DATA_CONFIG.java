package com.sailbright.airclean.enums;

public enum DATA_CONFIG {

    PM25_LEVEL("PM2.5_LEVEL");

    private DATA_CONFIG(String text) {
        this.text = text;
    }

    private String text;

    public String getText() {
        return text;
    }

}