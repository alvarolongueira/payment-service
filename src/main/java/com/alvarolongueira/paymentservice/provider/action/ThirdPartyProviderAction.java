package com.alvarolongueira.paymentservice.provider.action;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alvarolongueira.paymentservice.domain.Payment;
import com.alvarolongueira.paymentservice.exception.error.ValidateThirdPartyPaymentException;
import com.alvarolongueira.paymentservice.exception.model.ErrorPayment;
import com.alvarolongueira.paymentservice.exception.model.ErrorPaymentConverter;
import com.alvarolongueira.paymentservice.provider.ThirdPartyProvider;
import com.alvarolongueira.paymentservice.provider.action.model.ValidatePaymentRequest;
import lombok.NonNull;

@Service
public class ThirdPartyProviderAction implements ThirdPartyProvider {

    private URI validationUri;

    @Autowired
    private RestTemplateBuilder restTemplate;

    public ThirdPartyProviderAction(@Value("${external.system.payment.uri}") @NonNull URI validationUri) {
        this.validationUri = validationUri;
    }

    @Override
    public void validate(Payment payment) {
        ValidatePaymentRequest request = this.convert(payment);
        ResponseEntity<String> responseEntity = this.restTemplate.build().postForEntity(this.validationUri, request, String.class);

        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            ErrorPayment error = ErrorPaymentConverter.causeNetwork(payment, "Error validating third party");
            throw new ValidateThirdPartyPaymentException(error);
        }
    }

    private ValidatePaymentRequest convert(Payment payment) {
        return new ValidatePaymentRequest(payment.getUniqueId(), payment.getAccountId(),
                payment.getType().getCode(), payment.getCreditCard(), payment.getAmount().toString());
    }

}
