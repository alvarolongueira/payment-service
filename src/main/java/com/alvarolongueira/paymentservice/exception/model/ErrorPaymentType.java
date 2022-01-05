package com.alvarolongueira.paymentservice.exception.model;

public enum ErrorPaymentType {
    DATABASE("database"),
    NETWORK("network"),
    OTHER("other");

    private String code;

    ErrorPaymentType(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
