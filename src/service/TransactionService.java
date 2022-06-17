package service;

import entity.Account;
import entity.Transaction;
import repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static entity.SourceType.TRANSFER;
import static entity.SourceType.WITHDRAW;
import static entity.TransactionType.CREDIT;
import static entity.TransactionType.DEBIT;

public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    public TransactionService(TransactionRepository transactionRepository, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
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

    public boolean withdraw(int amount) {
        Account loggedAccount = accountService.getLoggedAccount();

        if (amount > loggedAccount.getBalance()) {
            System.out.println("Insufficient balance $" + amount);
            return false;
        }

        loggedAccount.setBalance(loggedAccount.getBalance() - amount);
        accountService.update(loggedAccount);
        transactionRepository.create(new Transaction(DEBIT, WITHDRAW, amount, loggedAccount.getAccountNumber(), LocalDateTime.now()));

        return true;
    }

    public boolean transfer(String accountNumber, int amount) {
        Account receiverAccount = accountService.findOne(accountNumber);
        Account senderAccount = accountService.getLoggedAccount();

        if (Objects.equals(receiverAccount.getAccountNumber(), "")) {
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
