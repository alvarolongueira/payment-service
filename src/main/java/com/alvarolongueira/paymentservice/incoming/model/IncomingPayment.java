package com.alvarolongueira.paymentservice.incoming.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class IncomingPayment {

    private String payment_id;

    private long account_id;

    private String payment_type;

    private String credit_card;

    private BigDecimal amount;

    private int delay;
}
