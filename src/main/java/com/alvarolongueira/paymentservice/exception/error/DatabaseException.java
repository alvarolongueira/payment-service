package com.alvarolongueira.paymentservice.exception.error;

import com.alvarolongueira.paymentservice.exception.PaymentServiceException;
import com.alvarolongueira.paymentservice.exception.model.ErrorPayment;

public class DatabaseException extends PaymentServiceException {

    private static final long serialVersionUID = 4227103989164194560L;

    public DatabaseException(ErrorPayment errorPayment) {
        super(errorPayment);
    }

}