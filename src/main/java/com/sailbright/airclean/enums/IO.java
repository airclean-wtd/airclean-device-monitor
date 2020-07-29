package com.sailbright.airclean.enums;

public enum IO {

    IN("I"),
    OUT("O");

    private String code;

    private IO(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}