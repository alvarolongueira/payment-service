package com.alvarolongueira.paymentservice.exception.error;

import com.alvarolongueira.paymentservice.exception.PaymentServiceException;
import com.alvarolongueira.paymentservice.exception.model.ErrorPayment;

public class PaymentTypeNotFoundException extends PaymentServiceException {

    private static final long serialVersionUID = 4227103989164194560L;

    public PaymentTypeNotFoundException(ErrorPayment errorPayment) {
        super(errorPayment);
    }
}
