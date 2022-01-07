package com.alvarolongueira.paymentservice.provider.action.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValidatePaymentRequest {

    public String payment_id;

    public long account_id;

    public String payment_type;

    public String credit_card;

    public String amount;

}
