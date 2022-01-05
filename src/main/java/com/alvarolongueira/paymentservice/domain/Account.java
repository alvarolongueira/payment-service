package com.alvarolongueira.paymentservice.domain;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class Account {

    private long id;

    private String mail;

    private Instant birthDate;

}
