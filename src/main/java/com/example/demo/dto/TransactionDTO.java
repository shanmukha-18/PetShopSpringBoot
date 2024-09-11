
package com.example.demo.dto;

import com.example.demo.enums.transaction_status;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionDTO {

    @NotNull(message = "Customer ID is mandatory")
    private Integer customerId;

    @NotNull(message = "Pet ID is mandatory")
    private Integer petId;

    @NotNull(message = "Transaction date is mandatory")
    private Date transactionDate;

    @NotNull(message = "Amount is mandatory")
    @Positive(message = "Amount must be positive")
    private BigDecimal amount;

    @NotNull(message = "Transaction status is mandatory")
    private transaction_status transactionStatus;

    // Getters and Setters

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public transaction_status getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(transaction_status transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
}
