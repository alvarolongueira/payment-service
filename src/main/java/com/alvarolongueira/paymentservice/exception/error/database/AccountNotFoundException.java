package com.alvarolongueira.paymentservice.exception.error.database;

public class AccountNotFoundException extends Exception {

    private static final long serialVersionUID = -8968668717174501214L;

    public AccountNotFoundException(String message) {
        super(message);
    }
}
