package com.alvarolongueira.paymentservice.exception.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class ErrorPayment {

    private String paymentId;

    private ErrorPaymentType type;

    private String description;

}
