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
        this.consumer.offlinePayment(this.message());
        Mockito.verify(this.offlineService, Mockito.times(1)).process(Mockito.any());
    }

    @Test
    public void success_online_payment() throws Exception {
        this.consumer.onlinePayment(this.message());
        Mockito.verify(this.onlineService, Mockito.times(1)).process(Mockito.any());
    }

    @Test
    public void parser_message_error() throws Exception {
        this.consumer.onlinePayment("this_message_has_to_break");
        Mockito.verify(this.handler, Mockito.times(1)).process(Mockito.any(Exception.class));
    }

    @Test
    public void payment_type_unknown() throws Exception {
        this.consumer.onlinePayment(this.message("invented"));
        Mockito.verify(this.handler, Mockito.times(1)).process(Mockito.any(PaymentServiceException.class));
    }

    private String message() {
        return this.message("offline");
    }

    private String message(String type) {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append("\"payment_id\": \"24929157-5069-43ae-996f-52faa43ce872\"");
        builder.append(",");
        builder.append("\"account_id\": 583");
        builder.append(",");
        builder.append("\"payment_type\": \"" + type + "\"");
        builder.append(",");
        builder.append("\"credit_card\": \"1234\"");
        builder.append(",");
        builder.append("\"amount\": 4");
        builder.append(",");
        builder.append("\"delay\": 110");
        builder.append("}");
        return builder.toString();
    }

}