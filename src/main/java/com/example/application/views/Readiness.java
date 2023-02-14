package com.example.application.views;

public enum Readiness {
    BG_1("Готовність № 1"),
    BG_2A("Готовність № 2а"),
    BG_2B("Готовність № 2б"),
    BG_3("Готовність № 3"),
    NONE("Дупа");

    public String value;

    Readiness(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
