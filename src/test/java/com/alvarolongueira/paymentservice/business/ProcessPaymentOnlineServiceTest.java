package com.alvarolongueira.paymentservice.business;

import org.junit.Assert;
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
import com.alvarolongueira.paymentservice.exception.PaymentServiceException;
import com.alvarolongueira.paymentservice.exception.model.ErrorPaymentType;
import com.alvarolongueira.paymentservice.mock.MockFactory;
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
        Payment mockPayment = MockFactory.payment();
        this.service.process(mockPayment);
        Mockito.verify(this.accountEntityManager, Mockito.times(1)).updateAccountDate(Mockito.anyLong());
        Mockito.verify(this.paymentEntityManager, Mockito.times(1)).insert(mockPayment);
        Mockito.verify(this.thirdPartyProvider, Mockito.times(1)).validate(mockPayment);
    }

    @Test
    public void network_exception_validating_third_party() throws Exception {
        boolean valid = false;
        Payment mockPayment = MockFactory.payment();
        Mockito.doThrow(new RuntimeException()).when(this.thirdPartyProvider).validate(mockPayment);

        try {
            this.service.process(mockPayment);
        } catch (PaymentServiceException exception) {
            if (ErrorPaymentType.NETWORK.equals(exception.getErrorPayment().getType())) {
                valid = true;
            }
        }
        Assert.assertTrue(valid);
    }

    @Test
    public void database_exception_saving_payment() throws Exception {
        boolean valid = false;
        Payment mockPayment = MockFactory.payment();
        Mockito.doThrow(new RuntimeException()).when(this.paymentEntityManager).insert(mockPayment);

        try {
            this.service.process(mockPayment);
        } catch (PaymentServiceException exception) {
            if (ErrorPaymentType.DATABASE.equals(exception.getErrorPayment().getType())) {
                valid = true;
            }
        }
        Assert.assertTrue(valid);
    }

    @Test
    public void database_exception_updating_account() throws Exception {
        boolean valid = false;
        Payment mockPayment = MockFactory.payment();
        Mockito.doThrow(new RuntimeException()).when(this.accountEntityManager).updateAccountDate(mockPayment.getAccountId());

        try {
            this.service.process(mockPayment);
        } catch (PaymentServiceException exception) {
            if (ErrorPaymentType.DATABASE.equals(exception.getErrorPayment().getType())) {
                valid = true;
            }
        }
        Assert.assertTrue(valid);
    }

}
