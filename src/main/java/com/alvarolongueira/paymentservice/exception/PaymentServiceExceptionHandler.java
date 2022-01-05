package com.alvarolongueira.paymentservice.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alvarolongueira.paymentservice.provider.NotifyProvider;

@Component
public class PaymentServiceExceptionHandler {

    @Autowired
    private NotifyProvider notifyProvider;

    @ExceptionHandler(PaymentServiceException.class)
    public void process(PaymentServiceException exception) {

        this.notifyProvider.notify(exception.getErrorPayment());
        
    }

}
