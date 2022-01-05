package com.alvarolongueira.paymentservice.domain;

public enum ErrorPaymentType {
    DATABASE("database"),
    NETWORK("network"),
    OTHER("other");

    private final String code;

    ErrorPaymentType(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
