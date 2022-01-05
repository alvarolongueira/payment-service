package com.alvarolongueira.paymentservice.repository.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PAYMENTS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity {

    @Id
    private String payment_id;

    @JoinColumn(name = "account_id")
    private long account_id;

    private String payment_type;

    private String credit_card;

    private BigDecimal amount;

    private Timestamp created_at;

}
