package com.alvarolongueira.paymentservice.repository;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.alvarolongueira.paymentservice.domain.Payment;
import com.alvarolongueira.paymentservice.exception.error.database.PaymentDuplicatedException;
import com.alvarolongueira.paymentservice.mock.MockFactory;
import com.alvarolongueira.paymentservice.repository.database.PaymentEntityManagerImpl;
import com.alvarolongueira.paymentservice.repository.database.PaymentEntityRepository;

@RunWith(MockitoJUnitRunner.class)
public class PaymentEntityManagerTest {

    @Mock
    private PaymentEntityRepository repository;

    private PaymentEntityManager manager;

    @Before
    public void setUp() {
        this.manager = new PaymentEntityManagerImpl((this.repository));
    }

    @Test
    public void success_save() throws PaymentDuplicatedException {
        Payment payment = MockFactory.paymentOnline();
        Mockito
                .when(this.repository.findById(payment.getUniqueId()))
                .thenReturn(Optional.empty());

        this.manager.insert(payment);

        Mockito.verify(this.repository).save(Mockito.any());
    }

    @Test(expected = PaymentDuplicatedException.class)
    public void exception_when_already_exist() throws PaymentDuplicatedException {
        Payment payment = MockFactory.paymentOnline();
        Mockito
                .when(this.repository.findById(payment.getUniqueId()))
                .thenReturn(Optional.of(MockFactory.paymentEntity()));

        this.manager.insert(payment);
    }

}
