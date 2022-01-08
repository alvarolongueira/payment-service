package com.alvarolongueira.paymentservice.consumer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.alvarolongueira.paymentservice.business.ProcessPaymentOfflineService;
import com.alvarolongueira.paymentservice.business.ProcessPaymentOnlineService;
import com.alvarolongueira.paymentservice.exception.PaymentServiceException;
import com.alvarolongueira.paymentservice.exception.PaymentServiceExceptionHandler;
import com.alvarolongueira.paymentservice.incoming.IncomingService;
import com.alvarolongueira.paymentservice.incoming.action.IncomingServiceAction;
import com.alvarolongueira.paymentservice.mock.MockFactory;

@RunWith(MockitoJUnitRunner.class)
public class KafkaConsumerTest {

    @Mock
    private ProcessPaymentOfflineService offlineService;
    @Mock
    private ProcessPaymentOnlineService onlineService;
    @Mock
    private PaymentServiceExceptionHandler handler;

    @InjectMocks
    private IncomingService service = new IncomingServiceAction();

    @InjectMocks
    private KafkaConsumer consumer;

    @Before
    public void setUp() {
        this.consumer = new KafkaConsumer(this.service);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void success_offline_payment() throws Exception {
        this.consumer.offlinePayment(MockFactory.kafkaMessage());
        Mockito.verify(this.offlineService, Mockito.times(1)).process(Mockito.any());
    }

    @Test
    public void success_online_payment() throws Exception {
        this.consumer.onlinePayment(MockFactory.kafkaMessage());
        Mockito.verify(this.onlineService, Mockito.times(1)).process(Mockito.any());
    }

    @Test
    public void parser_message_error() throws Exception {
        this.consumer.onlinePayment("this_message_has_to_break");
        Mockito.verify(this.handler, Mockito.times(1)).process(Mockito.any(Exception.class));
    }

    @Test
    public void payment_type_unknown() throws Exception {
        this.consumer.onlinePayment(MockFactory.kafkaMessage("1", "invented"));
        Mockito.verify(this.handler, Mockito.times(1)).process(Mockito.any(PaymentServiceException.class));
    }

}