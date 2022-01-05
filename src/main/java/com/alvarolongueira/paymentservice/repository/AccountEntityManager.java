package com.alvarolongueira.paymentservice.repository;

import com.alvarolongueira.paymentservice.exception.error.database.AccountNotFoundException;

public interface AccountEntityManager {

    void updateAccountDate(long accountId) throws AccountNotFoundException;

}
