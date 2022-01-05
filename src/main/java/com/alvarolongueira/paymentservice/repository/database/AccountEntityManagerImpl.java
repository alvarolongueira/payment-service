package com.alvarolongueira.paymentservice.repository.database;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.stereotype.Service;

import com.alvarolongueira.paymentservice.exception.error.database.AccountNotFoundException;
import com.alvarolongueira.paymentservice.repository.AccountEntityManager;
import com.alvarolongueira.paymentservice.repository.entity.AccountEntity;

@Service
public class AccountEntityManagerImpl implements AccountEntityManager {

    private final AccountEntityRepository repository;

    public AccountEntityManagerImpl(AccountEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public void updateAccountDate(long accountId) throws AccountNotFoundException {
        AccountEntity entity = this.repository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Account not exists with id: " + accountId));
        entity.setCreated_on(Timestamp.from(Instant.now()));
        this.repository.save(entity);
    }

}
