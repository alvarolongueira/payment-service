package com.alvarolongueira.paymentservice.provider.action;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.alvarolongueira.paymentservice.exception.model.ErrorPayment;
import com.alvarolongueira.paymentservice.provider.NotifyProvider;
import com.alvarolongueira.paymentservice.provider.action.model.ErrorPaymentRequestLogging;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NotifyProviderAction implements NotifyProvider {

    private URI errorUri;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private RestTemplateBuilder restTemplate;

    public NotifyProviderAction(@Value("${external.system.logger.uri}") @NonNull URI errorUri) {
        this.errorUri = errorUri;
    }

    @Override
    public void notify(ErrorPayment errorPayment) {
        log.error("Error: " + errorPayment);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(this.convert(errorPayment), headers);
        this.restTemplate.build().postForLocation(this.errorUri, entity);
    }

    private String convert(ErrorPayment errorPayment) {
        String json = "";
        ErrorPaymentRequestLogging request = new ErrorPaymentRequestLogging(errorPayment.getPaymentId(), errorPayment.getType().getCode(), errorPayment.getDescription());
        try {
            json = this.objectMapper.writeValueAsString(request);
        } catch (Exception e) {
            //TODO
        }
        return json;
    }

}
