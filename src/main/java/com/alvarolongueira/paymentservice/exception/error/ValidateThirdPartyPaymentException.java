package com.alvarolongueira.paymentservice.exception.error;

import com.alvarolongueira.paymentservice.exception.PaymentServiceException;
import com.alvarolongueira.paymentservice.exception.model.ErrorPayment;

public class ValidateThirdPartyPaymentException extends PaymentServiceException {

    private static final long serialVersionUID = 2079167267487034537L;

    public ValidateThirdPartyPaymentException(ErrorPayment errorPayment) {
        super(errorPayment);
    }

}