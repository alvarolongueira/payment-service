package com.alvarolongueira.paymentservice.exception.error;

import com.alvarolongueira.paymentservice.exception.PaymentServiceException;
import com.alvarolongueira.paymentservice.exception.model.ErrorPayment;

public class InvalidPaymentTypeException extends PaymentServiceException {

    private static final long serialVersionUID = 8004402356040361889L;

    public InvalidPaymentTypeException(ErrorPayment errorPayment) {
        super(errorPayment);
    }

}