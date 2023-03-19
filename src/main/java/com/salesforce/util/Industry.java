package com.salesforce.util;

public enum Industry {

    CLOTH("Одежда"),
    CHEMISTRY("Химия"),
    CONNECTION("Связь");

    private String title;

    Industry(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
