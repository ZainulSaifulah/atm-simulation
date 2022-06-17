package entity;

import java.time.LocalDateTime;

public class Transaction {
    private Long id;
    private TransactionType transactionType;
    private SourceType sourceType;
    private int amount;
    private String accountNumber;
    private LocalDateTime createdAt;

    public Transaction(Long id, TransactionType transactionType, SourceType sourceType, int amount, String accountNumber, LocalDateTime createdAt) {
        this.id = id;
        this.transactionType = transactionType;
        this.sourceType = sourceType;
        this.amount = amount;
        this.accountNumber = accountNumber;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public SourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(SourceType sourceType) {
        this.sourceType = sourceType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return String.format("%8s %8s %8s %5s %20s\n", id, transactionType, sourceType, amount, createdAt);
    }
}
