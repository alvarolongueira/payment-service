package com.alvarolongueira.paymentservice;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.alvarolongueira.paymentservice.consumer.KafkaConsumer;
import com.alvarolongueira.paymentservice.mock.MockFactory;
import com.alvarolongueira.paymentservice.repository.database.PaymentEntityRepository;
import com.alvarolongueira.paymentservice.repository.entity.PaymentEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
@ActiveProfiles(profiles = "test")
public class PaymentApplicationIntegrationTest {

    public static final String PAYMENT_ID = "HLE";

    //TODO emulate
    @Autowired
    private KafkaConsumer kafkaConsumer;

    @Autowired
    private PaymentEntityRepository paymentEntityRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        if (this.paymentEntityRepository.existsById(PAYMENT_ID)) {
            this.paymentEntityRepository.deleteById(PAYMENT_ID);
        }
    }

    @Test
    public void complete_and_successful_online_payment() throws Exception {
        Optional<PaymentEntity> entity = this.paymentEntityRepository.findById(PAYMENT_ID);
        Assert.assertTrue(!entity.isPresent());

        String message = MockFactory.kafkaMessage(PAYMENT_ID, "online");
        this.kafkaConsumer.onlinePayment(message);

        entity = this.paymentEntityRepository.findById(PAYMENT_ID);
        Assert.assertTrue(entity.isPresent());
    }

}
