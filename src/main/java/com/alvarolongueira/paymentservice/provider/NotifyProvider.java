package com.alvarolongueira.paymentservice.provider;

import com.alvarolongueira.paymentservice.exception.model.ErrorPayment;

public interface NotifyProvider {

    void notify(ErrorPayment errorPayment);

}
