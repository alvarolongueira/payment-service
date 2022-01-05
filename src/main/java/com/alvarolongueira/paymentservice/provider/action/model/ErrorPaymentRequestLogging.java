package com.alvarolongueira.paymentservice.provider.action.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorPaymentRequestLogging {

    public String payment_id;

    public String error_type;

    public String error_description;

}
