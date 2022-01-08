package com.alvarolongueira.paymentservice.repository;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.alvarolongueira.paymentservice.exception.error.database.AccountNotFoundException;
import com.alvarolongueira.paymentservice.mock.MockFactory;
import com.alvarolongueira.paymentservice.repository.database.AccountEntityManagerImpl;
import com.alvarolongueira.paymentservice.repository.database.AccountEntityRepository;
import com.alvarolongueira.paymentservice.repository.entity.AccountEntity;

@RunWith(MockitoJUnitRunner.class)
public class AccountEntityManagerTest {

    @Mock
    private AccountEntityRepository repository;

    private AccountEntityManager manager;

    @Before
    public void setUp() {
        this.manager = new AccountEntityManagerImpl((this.repository));
    }

    @Test
    public void success_update_only_last_payment_date() throws AccountNotFoundException {
        AccountEntity entity = MockFactory.accountEntity();
        Mockito
                .when(this.repository.findById(entity.getAccount_id()))
                .thenReturn(Optional.of(entity));

        this.manager.updateAccountDate(entity.getAccount_id());

        Mockito.verify(this.repository).save(entity);
        Assert.assertTrue(this.compareOnlyChangedDate(MockFactory.accountEntity(), entity));
    }

    @Test(expected = AccountNotFoundException.class)
    public void exception_not_found_account() throws AccountNotFoundException {
        AccountEntity entity = MockFactory.accountEntity();
        Mockito
                .when(this.repository.findById(entity.getAccount_id()))
                .thenReturn(Optional.empty());

        this.manager.updateAccountDate(entity.getAccount_id());
    }

    private boolean compareOnlyChangedDate(AccountEntity entity1, AccountEntity entity2) {

        //these should be equals
        if (Long.compare(entity1.getAccount_id(), entity2.getAccount_id()) != 0) {
            return false;
        }
        if (!entity1.getEmail().equals(entity2.getEmail())) {
            return false;
        }
        if (!entity1.getBirthdate().equals(entity2.getBirthdate())) {
            return false;
        }
        if (!entity1.getCreated_on().equals(entity2.getCreated_on())) {
            return false;
        }

        //this should change
        if (entity1.getLast_payment_date().equals(entity2.getLast_payment_date())) {
            return false;
        }

        return true;
    }

}
