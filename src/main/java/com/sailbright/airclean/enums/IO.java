package com.sailbright.airclean.enums;

public enum IO {

    IN("IN"),
    OUT("OUT");

    private String code;

    private IO(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}