package com.alvarolongueira.paymentservice.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alvarolongueira.paymentservice.provider.NotifyProvider;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PaymentServiceExceptionHandler {

    @Autowired
    private NotifyProvider notifyProvider;

    @ExceptionHandler(PaymentServiceException.class)
    public void process(PaymentServiceException exception) {
        log.error("Error: " + exception.getErrorPayment());
        this.notifyProvider.notify(exception.getErrorPayment());

    }

    @ExceptionHandler(Exception.class)
    public void process(Exception exception) {
        log.error("Generic unhandled error: " + exception.getMessage());
    }

}
