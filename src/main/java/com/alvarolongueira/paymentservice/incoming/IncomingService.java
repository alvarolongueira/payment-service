package com.alvarolongueira.paymentservice.incoming;

import com.alvarolongueira.paymentservice.incoming.model.IncomingPayment;

public interface IncomingService {

    void processOnlinePayment(IncomingPayment payment);

    void processOfflinePayment(IncomingPayment payment);

}