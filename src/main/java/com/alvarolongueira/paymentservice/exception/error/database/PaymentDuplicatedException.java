package com.alvarolongueira.paymentservice.exception.error.database;

public class PaymentDuplicatedException extends Exception {

    private static final long serialVersionUID = -8968668717174501214L;

    public PaymentDuplicatedException(String message) {
        super(message);
    }
}
