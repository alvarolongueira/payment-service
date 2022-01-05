package com.alvarolongueira.paymentservice.exception;

import com.alvarolongueira.paymentservice.exception.model.ErrorPayment;

public abstract class PaymentServiceException extends RuntimeException {

    private static long serialVersionUID = -6599889134699424078L;

    private ErrorPayment errorPayment;

    public PaymentServiceException(ErrorPayment errorPayment) {
        this.errorPayment = errorPayment;
    }

    public ErrorPayment getErrorPayment() {
        return this.errorPayment;
    }

}
