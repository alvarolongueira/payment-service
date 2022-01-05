package com.alvarolongueira.paymentservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class ErrorPayment {

    private long paymentId;

    private ErrorPaymentType type;

    private String description;

}
