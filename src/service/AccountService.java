package service;

import data.DummyAccountData;
import entity.Account;

import java.util.List;

public class AccountService {
    private final List<Account> accounts = new DummyAccountData().getData();
    public List<Account> findAll() {
        return accounts;
    }

    public Account findOne(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public boolean withdraw(String accountNumber, int amount) {
        Account account = this.findOne(accountNumber);

        if (amount > account.getBalance()) {
            System.out.println("Insufficient balance $" + amount);
            return false;
        }

        account.setBalance(account.getBalance() - amount);
        return true;
    }

    public boolean transfer(String senderAccountNumber, String receiverAccountNumber, int amount) {
        Account senderAccount = findOne(senderAccountNumber);
        Account receiverAccount = findOne(receiverAccountNumber);

        if (receiverAccount == null) {
            System.out.println("Invalid account");
            return false;
        }

        if (amount > senderAccount.getBalance()) {
            System.out.println("Insufficient balance $" + amount);
            return false;
        }

        senderAccount.setBalance(senderAccount.getBalance() - amount);
        receiverAccount.setBalance(receiverAccount.getBalance() + amount);
        return true;
    }
}
