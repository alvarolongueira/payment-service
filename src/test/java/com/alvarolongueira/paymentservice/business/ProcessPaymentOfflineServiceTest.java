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

import com.alvarolongueira.paymentservice.business.action.ProcessPaymentOfflineServiceAction;
import com.alvarolongueira.paymentservice.domain.Payment;
import com.alvarolongueira.paymentservice.exception.PaymentServiceException;
import com.alvarolongueira.paymentservice.exception.model.ErrorPaymentType;
import com.alvarolongueira.paymentservice.mock.MockFactory;
import com.alvarolongueira.paymentservice.repository.AccountEntityManager;
import com.alvarolongueira.paymentservice.repository.PaymentEntityManager;

@RunWith(MockitoJUnitRunner.class)
public class ProcessPaymentOfflineServiceTest {

    @Mock
    private AccountEntityManager accountEntityManager;
    @Mock
    private PaymentEntityManager paymentEntityManager;

    @InjectMocks
    private ProcessPaymentOfflineService service = new ProcessPaymentOfflineServiceAction();

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void success() throws Exception {
        Payment mockPayment = MockFactory.paymentOffline();
        this.service.process(mockPayment);
        Mockito.verify(this.accountEntityManager, Mockito.times(1)).updateAccountDate(Mockito.anyLong());
        Mockito.verify(this.paymentEntityManager, Mockito.times(1)).insert(mockPayment);
    }

    @Test
    public void database_exception_saving_payment() throws Exception {
        boolean valid = false;
        Payment mockPayment = MockFactory.paymentOffline();
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
        Payment mockPayment = MockFactory.paymentOffline();
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

    @Test
    public void exception_wrong_payment_type() throws Exception {
        boolean valid = false;
        Payment mockPayment = MockFactory.paymentOnline();

        try {
            this.service.process(mockPayment);
        } catch (PaymentServiceException exception) {
            if (ErrorPaymentType.OTHER.equals(exception.getErrorPayment().getType())) {
                valid = true;
            }
        }
        Assert.assertTrue(valid);
    }

}
