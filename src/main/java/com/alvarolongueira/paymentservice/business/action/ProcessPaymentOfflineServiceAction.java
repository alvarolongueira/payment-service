package com.alvarolongueira.paymentservice.business.action;

import org.springframework.stereotype.Service;

import com.alvarolongueira.paymentservice.business.ProcessPaymentOfflineService;
import com.alvarolongueira.paymentservice.domain.Payment;

@Service
public class ProcessPaymentOfflineServiceAction implements ProcessPaymentOfflineService {

    @Override
    public void process(Payment payment) {

    }

}
