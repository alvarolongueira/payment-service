package com.alvarolongueira.paymentservice.domain;

public enum PaymentType {
    ONLINE("online"),
    OFFLINE("offline");

    private final String code;

    PaymentType(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
