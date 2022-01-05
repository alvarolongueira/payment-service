package com.alvarolongueira.paymentservice.repository.database;

import org.springframework.data.repository.CrudRepository;

import com.alvarolongueira.paymentservice.repository.entity.AccountEntity;

public interface AccountEntityRepository extends CrudRepository<AccountEntity, Long> {

}
