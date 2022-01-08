package com.alvarolongueira.paymentservice.business.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alvarolongueira.paymentservice.business.ProcessPaymentOfflineService;
import com.alvarolongueira.paymentservice.domain.Payment;
import com.alvarolongueira.paymentservice.domain.PaymentType;
import com.alvarolongueira.paymentservice.exception.error.DatabaseException;
import com.alvarolongueira.paymentservice.exception.error.InvalidPaymentTypeException;
import com.alvarolongueira.paymentservice.exception.model.ErrorPayment;
import com.alvarolongueira.paymentservice.exception.model.ErrorPaymentConverter;
import com.alvarolongueira.paymentservice.repository.AccountEntityManager;
import com.alvarolongueira.paymentservice.repository.PaymentEntityManager;

@Service
public class ProcessPaymentOfflineServiceAction implements ProcessPaymentOfflineService {

    @Autowired
    private AccountEntityManager accountEntityManager;

    @Autowired
    private PaymentEntityManager paymentEntityManager;

    public ProcessPaymentOfflineServiceAction() {

    }

    @Override
    public void process(Payment payment) {
        this.validate(payment);
        this.updateAndSaveEntities(payment);
    }

    private void validate(Payment payment) {
        if (!PaymentType.OFFLINE.equals(payment.getType())) {
            ErrorPayment error = ErrorPaymentConverter.causeOther(payment, "Received online payment with wrong type: " + payment.getType());
            throw new InvalidPaymentTypeException(error);
        }
    }

    private void updateAndSaveEntities(Payment payment) {
        try {

            this.paymentEntityManager.insert(payment);
            this.accountEntityManager.updateAccountDate(payment.getAccountId());

        } catch (Exception e) {
            ErrorPayment error = ErrorPaymentConverter.causeDataBase(payment, e.getMessage());
            throw new DatabaseException(error);
        }
    }


}
