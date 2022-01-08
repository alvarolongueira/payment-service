package com.alvarolongueira.paymentservice.mock;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;

import com.alvarolongueira.paymentservice.domain.Payment;
import com.alvarolongueira.paymentservice.domain.PaymentType;
import com.alvarolongueira.paymentservice.repository.entity.AccountEntity;
import com.alvarolongueira.paymentservice.repository.entity.PaymentEntity;

public class MockFactory {

    public static Payment paymentOnline() {
        return new Payment("A1", 1L, PaymentType.ONLINE, "1234", BigDecimal.TEN);
    }

    public static Payment paymentOffline() {
        return new Payment("B2", 2L, PaymentType.OFFLINE, "5678", BigDecimal.ONE);
    }

    public static PaymentEntity paymentEntity() {
        Timestamp createdOn = Timestamp.from(Instant.parse("1995-01-01T18:00:00.00Z"));
        return new PaymentEntity("A1", 1L, "online", "1234", BigDecimal.TEN, createdOn);
    }

    public static AccountEntity accountEntity() {
        Date birthdate = new Date(Instant.parse("1980-01-01T18:00:00.00Z").toEpochMilli());
        Timestamp lastPayment = Timestamp.from(Instant.parse("1990-01-01T18:00:00.00Z"));
        Timestamp createdOn = Timestamp.from(Instant.parse("1993-01-01T18:00:00.00Z"));
        return new AccountEntity(1L, "mail@mail.com", birthdate, lastPayment, createdOn);
    }


    public static String kafkaMessage() {
        return kafkaMessage("HL2", "offline");
    }

    public static String kafkaMessage(String paymentId, String type) {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append("\"payment_id\": \"" + paymentId + "\"");
        builder.append(",");
        builder.append("\"account_id\": 123");
        builder.append(",");
        builder.append("\"payment_type\": \"" + type + "\"");
        builder.append(",");
        builder.append("\"credit_card\": \"1234 5678\"");
        builder.append(",");
        builder.append("\"amount\": 16");
        builder.append(",");
        builder.append("\"delay\": 21");
        builder.append("}");
        return builder.toString();
    }


}
