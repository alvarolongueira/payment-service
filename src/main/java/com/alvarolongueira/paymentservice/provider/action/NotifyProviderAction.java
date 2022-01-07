package com.alvarolongueira.paymentservice.provider.action;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alvarolongueira.paymentservice.exception.model.ErrorPayment;
import com.alvarolongueira.paymentservice.provider.NotifyProvider;
import com.alvarolongueira.paymentservice.provider.action.model.NotifyLoggingErrorPaymentRequest;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NotifyProviderAction implements NotifyProvider {

    private URI errorUri;

    @Autowired
    private RestTemplateBuilder restTemplate;


    public NotifyProviderAction(@Value("${external.system.logger.uri}") @NonNull URI errorUri) {
        this.errorUri = errorUri;
    }

    @Override
    public void notify(ErrorPayment errorPayment) {
        try {
            NotifyLoggingErrorPaymentRequest request = this.convert(errorPayment);
            ResponseEntity<String> responseEntity = this.restTemplate.build().postForEntity(this.errorUri, request, String.class);
            if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
                log.error("Error notifying external service" + request);
            }
        } catch (Exception e) {
            log.error("Error notifying external service" + errorPayment);
        }
    }

    private NotifyLoggingErrorPaymentRequest convert(ErrorPayment errorPayment) {
        return new NotifyLoggingErrorPaymentRequest(errorPayment.getPaymentId(), errorPayment.getType().getCode(), errorPayment.getDescription());
    }
}
