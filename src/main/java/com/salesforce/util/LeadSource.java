package com.salesforce.util;

public enum LeadSource {

    OTHER("Другое"),
    WEB("Веб"),
    SOCIAL("Social");

    private String title;

    LeadSource(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
