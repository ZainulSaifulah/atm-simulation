package com.zainul.atm.service;

import com.zainul.atm.entity.Account;
import com.zainul.atm.entity.Transaction;
import com.zainul.atm.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import static com.zainul.atm.entity.SourceType.TRANSFER;
import static com.zainul.atm.entity.SourceType.WITHDRAW;
import static com.zainul.atm.entity.TransactionType.CREDIT;
import static com.zainul.atm.entity.TransactionType.DEBIT;

public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    public TransactionService() {
        this.transactionRepository = new TransactionRepository();
        this.accountService = new AccountService();
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionHistory(String accountNumber) {
        return transactionRepository.findAll()
                .stream()
                .filter(transaction -> transaction.getAccountNumber().equals(accountNumber))
                .sorted(Comparator.comparing(Transaction::getCreatedAt))
                .limit(10)
                .toList();
    }

    public boolean withdraw(String accountNumber, int amount) {
        Account loggedAccount = accountService.findOne(accountNumber);

        if (amount > loggedAccount.getBalance()) {
            System.out.println("Insufficient balance $" + amount);
            return false;
        }

        loggedAccount.setBalance(loggedAccount.getBalance() - amount);
        accountService.update(loggedAccount);
        transactionRepository.create(new Transaction(DEBIT, WITHDRAW, amount, loggedAccount.getAccountNumber(), LocalDateTime.now()));

        return true;
    }

    public boolean transfer(String senderAccountNumber, String receiverAccountNumber, int amount) {
        Account senderAccount = accountService.findOne(senderAccountNumber);
        Account receiverAccount = accountService.findOne(receiverAccountNumber);

        if (receiverAccount == null) {
            System.out.println("Invalid account");
            return false;
        }

        if (amount > senderAccount.getBalance()) {
            System.out.println("Insufficient balance $" + amount);
            return false;
        }

        senderAccount.setBalance(senderAccount.getBalance() - amount);
        accountService.update(senderAccount);
        transactionRepository.create(new Transaction(DEBIT, TRANSFER, amount, senderAccount.getAccountNumber(), LocalDateTime.now()));

        receiverAccount.setBalance(receiverAccount.getBalance() + amount);
        accountService.update(receiverAccount);
        transactionRepository.create(new Transaction(CREDIT, TRANSFER, amount, receiverAccount.getAccountNumber(), LocalDateTime.now()));
        return true;
    }
}
