package com.alvarolongueira.paymentservice.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "com.alvarolongueira.paymentservice")
public class PaymentServiceExceptionHandler {

    @ExceptionHandler(PaymentServiceException.class)
    public void handleException(PaymentServiceException exception) {
        //TODO this
    }

}
