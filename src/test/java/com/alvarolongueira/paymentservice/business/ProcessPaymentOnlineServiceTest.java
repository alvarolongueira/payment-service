package com.alvarolongueira.paymentservice.business;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.alvarolongueira.paymentservice.business.action.ProcessPaymentOnlineServiceAction;
import com.alvarolongueira.paymentservice.domain.Payment;
import com.alvarolongueira.paymentservice.domain.PaymentType;
import com.alvarolongueira.paymentservice.provider.ThirdPartyProvider;
import com.alvarolongueira.paymentservice.repository.AccountEntityManager;
import com.alvarolongueira.paymentservice.repository.PaymentEntityManager;

@RunWith(MockitoJUnitRunner.class)

public class ProcessPaymentOnlineServiceTest {

    @Mock
    private AccountEntityManager accountEntityManager;
    @Mock
    private PaymentEntityManager paymentEntityManager;
    @Mock
    private ThirdPartyProvider thirdPartyProvider;

    @InjectMocks
    private ProcessPaymentOnlineService service = new ProcessPaymentOnlineServiceAction();

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void success() throws Exception {
        Payment mockPayment = this.payment();
        this.service.process(mockPayment);
        Mockito.verify(this.accountEntityManager, Mockito.times(1)).updateAccountDate(Mockito.anyLong());
        Mockito.verify(this.paymentEntityManager, Mockito.times(1)).insert(mockPayment);
        Mockito.verify(this.thirdPartyProvider, Mockito.times(1)).validate(mockPayment);
    }

    private Payment payment() {
        return new Payment("A1", 1L, PaymentType.ONLINE, "1234", BigDecimal.TEN);
    }
}
