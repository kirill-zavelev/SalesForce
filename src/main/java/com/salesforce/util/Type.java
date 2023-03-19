package com.salesforce.util;

public enum Type {

    ANALYST("Аналитик"),
    INVESTOR("Инвестор"),
    COMPETITOR("Конкурент"),
    CLIENT("Клиент"),
    INTEGRATOR("Интегратор"),
    PARTNER("Партнер"),
    PRESS("Пресса");

    private String title;

    Type(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
