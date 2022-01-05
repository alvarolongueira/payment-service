package com.alvarolongueira.paymentservice.repository.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
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
    @Column(name = "payment_id")
    private String payment_id;

    @JoinColumn(name = "account_id")
    private long account_id;

    @Column(name = "payment_type")
    private String payment_type;

    @Column(name = "credit_card")
    private String credit_card;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "created_on")
    private Timestamp created_on;

}
