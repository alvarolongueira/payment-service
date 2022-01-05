package com.alvarolongueira.paymentservice.incoming.action;

import org.springframework.stereotype.Component;

import com.alvarolongueira.paymentservice.incoming.IncomingService;
import com.alvarolongueira.paymentservice.incoming.model.IncomingPayment;

@Component
public class IncomingServiceAction implements IncomingService {

    @Override
    public void processOnlinePayment(IncomingPayment payment) {

    }

    @Override
    public void processOfflinePayment(IncomingPayment payment) {

    }
}
