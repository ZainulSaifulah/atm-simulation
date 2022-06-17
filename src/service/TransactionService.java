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
    private final List<Transaction> transactions;
    private final AccountService accountService;

    public TransactionService(TransactionRepository transactionRepository, AccountService accountService) {
        this.transactions = transactionRepository.findAll();
        this.accountService = accountService;
    }

    public List<Transaction> findAll() {
        return transactions;
    }

    public List<Transaction> getTransactionHistory(String accountNumber) {
        return transactions
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
        transactions.add(new Transaction(getId(), DEBIT, WITHDRAW, amount, loggedAccount.getAccountNumber(), LocalDateTime.now()));

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
        transactions.add(new Transaction(getId(), DEBIT, TRANSFER, amount, senderAccount.getAccountNumber(), LocalDateTime.now()));

        receiverAccount.setBalance(receiverAccount.getBalance() + amount);
        transactions.add(new Transaction(getId(), CREDIT, TRANSFER, amount, receiverAccount.getAccountNumber(), LocalDateTime.now()));
        return true;
    }

    private Long getId() {
        return transactions.size() + 1L;
    }
}
