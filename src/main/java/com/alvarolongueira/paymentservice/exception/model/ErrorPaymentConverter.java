package com.alvarolongueira.paymentservice.exception.model;

import com.alvarolongueira.paymentservice.domain.Payment;
import com.alvarolongueira.paymentservice.incoming.model.IncomingPayment;

public class ErrorPaymentConverter {

    public static ErrorPayment causeOther(IncomingPayment incoming, String description) {
        return new ErrorPayment(incoming.getPayment_id(), ErrorPaymentType.OTHER, description);
    }

    public static ErrorPayment causeDataBase(Payment payment, String description) {
        return new ErrorPayment(payment.getUniqueId(), ErrorPaymentType.DATABASE, description);
    }

    public static ErrorPayment causeNetwork(Payment payment, String description) {
        return new ErrorPayment(payment.getUniqueId(), ErrorPaymentType.NETWORK, description);
    }

    public static ErrorPayment causeOther(Payment payment, String description) {
        return new ErrorPayment(payment.getUniqueId(), ErrorPaymentType.OTHER, description);
    }
}
