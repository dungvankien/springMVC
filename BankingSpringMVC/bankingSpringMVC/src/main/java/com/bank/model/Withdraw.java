package com.bank.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "withdraws")
public class Withdraw extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "transaction_amount", precision = 12, scale = 0, nullable = false)
    private BigDecimal transactionAmount;

    public Withdraw() {
    }

    public Withdraw(Long id, Long customerId, BigDecimal transactionAmount) {
        this.id = id;
        this.customerId = customerId;
        this.transactionAmount = transactionAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
}
