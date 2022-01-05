package com.alvarolongueira.paymentservice.business;

import com.alvarolongueira.paymentservice.domain.Payment;

public interface ProcessPaymentOfflineService {

    void process(Payment payment);

}
