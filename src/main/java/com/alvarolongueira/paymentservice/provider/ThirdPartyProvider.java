package com.alvarolongueira.paymentservice.provider;

import com.alvarolongueira.paymentservice.domain.Payment;

public interface ThirdPartyProvider {

    void validate(Payment payment);

}
