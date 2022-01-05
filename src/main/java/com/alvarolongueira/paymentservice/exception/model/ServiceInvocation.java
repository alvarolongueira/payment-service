package com.alvarolongueira.paymentservice.exception.model;

import com.alvarolongueira.paymentservice.exception.PaymentServiceException;

public interface ServiceInvocation {

    void invoke() throws PaymentServiceException;

}
