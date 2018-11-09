package com.jlizarazo.jpmorgan.model;

public enum Operation {

    BUY("B"),
    SELL("S");

    private final String code;

    private Operation(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

}
