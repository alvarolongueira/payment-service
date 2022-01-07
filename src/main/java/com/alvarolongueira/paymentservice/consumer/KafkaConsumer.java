package com.alvarolongueira.paymentservice.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

import com.alvarolongueira.paymentservice.exception.PaymentServiceException;
import com.alvarolongueira.paymentservice.exception.PaymentServiceExceptionHandler;
import com.alvarolongueira.paymentservice.exception.model.ServiceInvocation;
import com.alvarolongueira.paymentservice.incoming.IncomingService;
import com.alvarolongueira.paymentservice.incoming.model.IncomingPayment;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class KafkaConsumer {

    private IncomingService incomingService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private PaymentServiceExceptionHandler exceptionHandler;

    public KafkaConsumer(IncomingService incomingService) {
        this.incomingService = incomingService;
    }

    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}", topics = "${kafka.topic.onlinePayment}")
    public void onlinePayment(String message) throws Exception {
        this.handler(() -> {
            log.debug("Message onlinePayment received: " + message);
            IncomingPayment payment = this.objectMapper.readValue(message, IncomingPayment.class);
            this.incomingService.processOnlinePayment(payment);
        });
    }

    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}", topics = "${kafka.topic.offlinePayment}")
    public void offlinePayment(String message) throws Exception {
        this.handler(() -> {
            log.debug("Message offlinePayment received: " + message);
            IncomingPayment payment = this.objectMapper.readValue(message, IncomingPayment.class);
            this.incomingService.processOfflinePayment(payment);
        });
    }

    private void handler(ServiceInvocation invocation) {
        try {
            invocation.invoke();
        } catch (PaymentServiceException exception) {
            this.exceptionHandler.process(exception);
        } catch (Exception exception) {
            this.exceptionHandler.process(exception);
        }
    }

}
