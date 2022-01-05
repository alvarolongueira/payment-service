package com.alvarolongueira.paymentservice.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class Payment {

    private String uniqueId;

    private long accountId;

    private PaymentType type;

    private String creditCard;

    private BigDecimal amount;

}
