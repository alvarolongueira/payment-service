package com.alvarolongueira.paymentservice.business;

import com.alvarolongueira.paymentservice.domain.Payment;

public interface ProcessPaymentOnlineService {

    void process(Payment payment);

}
