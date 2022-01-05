package com.alvarolongueira.paymentservice.repository;

import com.alvarolongueira.paymentservice.domain.Payment;
import com.alvarolongueira.paymentservice.exception.error.database.PaymentDuplicatedException;

public interface PaymentEntityManager {

    void insert(Payment payment) throws PaymentDuplicatedException;

}
