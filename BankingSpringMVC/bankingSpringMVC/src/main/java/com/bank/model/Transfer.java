package com.bank.model;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Transactional
public class Transfer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int fees;

    @Column(name = "fees_amount", precision = 12, scale = 0, nullable = false)
    private BigDecimal feesAmount;

    @Column(name = "transaction_amount", precision = 12, scale = 0, nullable = false)
    private BigDecimal transactionAmount;

    @Column(name = "transfer_amount", precision = 12, scale = 0,nullable = false)
    private BigDecimal transferAmount;

    @Column(name = "recipient_id")
    private Long recipientId;

    @Column(name = "sender_id")
    private Long senderId;

    private String senderName;

    private String recipientName;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public Transfer() {
    }

    public Transfer(Long id, int fees, BigDecimal feesAmount, BigDecimal transactionAmount, BigDecimal transferAmount, Long recipientId, Long senderId) {
        this.id = id;
        this.fees = fees;
        this.feesAmount = feesAmount;
        this.transactionAmount = transactionAmount;
        this.transferAmount = transferAmount;
        this.recipientId = recipientId;
        this.senderId = senderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }

    public BigDecimal getFeesAmount() {
        return feesAmount;
    }

    public void setFeesAmount(BigDecimal feesAmount) {
        this.feesAmount = feesAmount;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }
}
