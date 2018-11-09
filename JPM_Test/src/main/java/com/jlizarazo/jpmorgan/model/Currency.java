package com.jlizarazo.jpmorgan.model;

public enum Currency {

    SGP("Singapore Dollar"),
    AED("Emirati Dirham"),
    SAR("Saudi Arabian Riyal"),
    GBP("Pound Sterling"),
    EUR("Euro"),
    USD("United States Dollar");

    private final String name;

    private Currency(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
