package com.alvarolongueira.paymentservice.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
public class PaymentServiceExceptionHandler {

    @ExceptionHandler(PaymentServiceException.class)
    public void process(PaymentServiceException exception) {
        //TODO this

        System.err.println("CUSTOM ERROR -> " + exception.getErrorPayment());

    }

}
