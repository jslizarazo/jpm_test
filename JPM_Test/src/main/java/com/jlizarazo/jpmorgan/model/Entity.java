package com.jlizarazo.jpmorgan.model;

public enum Entity {

    CHB("Chemical Bank"),
    CMB("Chase Manhattan Bank"),
    BOC("Banc One Corp"),
    BUT("Bank United of Texas"),
    WSM("Washington Mutual");

    private final String name;

    private Entity(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
