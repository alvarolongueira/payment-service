package com.alvarolongueira.paymentservice.domain;

import java.util.Optional;

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


    public static Optional<PaymentType> of(String paymentType) throws IllegalArgumentException {
        for (PaymentType current : PaymentType.values()) {
            if (current.getCode().equals(paymentType)) {
                return Optional.of(current);
            }
        }
        return Optional.empty();
    }
}
