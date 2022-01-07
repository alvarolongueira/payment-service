package com.alvarolongueira.paymentservice.incoming.action;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alvarolongueira.paymentservice.business.ProcessPaymentOfflineService;
import com.alvarolongueira.paymentservice.business.ProcessPaymentOnlineService;
import com.alvarolongueira.paymentservice.domain.Payment;
import com.alvarolongueira.paymentservice.domain.PaymentType;
import com.alvarolongueira.paymentservice.exception.error.PaymentTypeNotFoundException;
import com.alvarolongueira.paymentservice.exception.model.ErrorPayment;
import com.alvarolongueira.paymentservice.exception.model.ErrorPaymentConverter;
import com.alvarolongueira.paymentservice.incoming.IncomingService;
import com.alvarolongueira.paymentservice.incoming.model.IncomingPayment;

@Component
public class IncomingServiceAction implements IncomingService {

    @Autowired
    private ProcessPaymentOnlineService onlineService;

    @Autowired
    private ProcessPaymentOfflineService offlineService;

    @Override
    public void processOnlinePayment(IncomingPayment incoming) {
        Payment payment = this.convert(incoming);
        this.onlineService.process(payment);
    }

    @Override
    public void processOfflinePayment(IncomingPayment incoming) {
        Payment payment = this.convert(incoming);
        this.offlineService.process(payment);
    }

    private Payment convert(IncomingPayment incoming) {
        Optional<PaymentType> paymentType = PaymentType.of(incoming.getPayment_type());
        if (!paymentType.isPresent()) {
            ErrorPayment error = ErrorPaymentConverter.causeOther(incoming, "Payment type not found " + incoming.getPayment_type());
            throw new PaymentTypeNotFoundException(error);
        }
        return new Payment(incoming.getPayment_id(), incoming.getAccount_id(), paymentType.get(), incoming.getCredit_card(), incoming.getAmount());
    }

}
