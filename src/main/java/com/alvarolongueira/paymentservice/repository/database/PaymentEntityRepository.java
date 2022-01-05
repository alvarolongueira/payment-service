package com.alvarolongueira.paymentservice.repository.database;

import org.springframework.data.repository.CrudRepository;

import com.alvarolongueira.paymentservice.repository.entity.PaymentEntity;

public interface PaymentEntityRepository extends CrudRepository<PaymentEntity, Long> {

}
