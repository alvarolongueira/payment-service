package com.alvarolongueira.paymentservice.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

import com.alvarolongueira.paymentservice.incoming.IncomingService;
import com.alvarolongueira.paymentservice.incoming.model.IncomingPayment;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class KafkaConsumer {

    private final IncomingService incomingService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public KafkaConsumer(IncomingService incomingService) {
        this.incomingService = incomingService;
    }

    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}", topics = "${kafka.topic.onlinePayment}")
    public void onlinePayment(String message) throws Exception {
        System.err.println("onlinePayment -> " + message);
        IncomingPayment payment = this.objectMapper.readValue(message, IncomingPayment.class);
        this.incomingService.processOnlinePayment(payment);
    }

    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}", topics = "${kafka.topic.offlinePayment}")
    public void offlinePayment(String message) throws Exception {
        System.err.println("onlinePayment -> " + message);
        IncomingPayment payment = this.objectMapper.readValue(message, IncomingPayment.class);
        this.incomingService.processOfflinePayment(payment);
    }

}
