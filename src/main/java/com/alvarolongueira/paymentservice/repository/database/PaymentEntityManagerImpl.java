package com.alvarolongueira.paymentservice.repository.database;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.alvarolongueira.paymentservice.domain.Payment;
import com.alvarolongueira.paymentservice.exception.error.database.PaymentDuplicatedException;
import com.alvarolongueira.paymentservice.repository.PaymentEntityManager;
import com.alvarolongueira.paymentservice.repository.entity.PaymentEntity;

@Service
public class PaymentEntityManagerImpl implements PaymentEntityManager {

    private PaymentEntityRepository repository;

    public PaymentEntityManagerImpl(PaymentEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public void insert(Payment payment) throws PaymentDuplicatedException {
        Optional<PaymentEntity> oldEntity = this.repository.findById(payment.getUniqueId());
        if (oldEntity.isPresent()) {
            throw new PaymentDuplicatedException("Payment already exists with id: " + payment.getUniqueId());
        }

        PaymentEntity entity = this.convert(payment);
        this.repository.save(entity);
    }

    private PaymentEntity convert(Payment payment) {
        return new PaymentEntity(payment.getUniqueId(), payment.getAccountId(), payment.getType().getCode(),
                payment.getCreditCard(), payment.getAmount(), Timestamp.from(Instant.now()));
    }

}
