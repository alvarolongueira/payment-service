package com.alvarolongueira.paymentservice.business.action;

import org.springframework.stereotype.Service;

import com.alvarolongueira.paymentservice.business.ProcessPaymentOnlineService;
import com.alvarolongueira.paymentservice.domain.Payment;

@Service
public class ProcessPaymentOnlineServiceAction implements ProcessPaymentOnlineService {

    @Override
    public void process(Payment payment) {

    }

}
