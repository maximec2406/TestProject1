package ru.abr.dit.Enums;

public enum EnumCountry {

    Russia("Россия"),
    Not_Russia("Не Россия");

    private final String dbValue;

    EnumCountry(String dbValue) {
        this.dbValue = dbValue;
    }

    public String getDbValue() {
        return dbValue;
    }
}