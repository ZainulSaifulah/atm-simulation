package service;

import data.DummyAccountData;
import entity.Account;

import java.util.List;

public class AccountService {
    private final List<Account> accounts;
    private Account loggedAccount;

    public AccountService(DummyAccountData dummyAccountData) {
        this.accounts = dummyAccountData.getData();
    }

    public List<Account> findAll() {
        return accounts;
    }

    public Account findOne(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return new Account("", "", 0, "");
    }

    public boolean login(String accountNumber, String pin) {
        Account account = findOne(accountNumber);

        return account.getPin().equals(pin) && setLoggedAccount(account);
    }

    private boolean setLoggedAccount(Account account) {
        this.loggedAccount = account;
        return true;
    }

    public Account getLoggedAccount() {
        return this.loggedAccount;
    }

    public boolean withdraw(int amount) {
        if (amount > loggedAccount.getBalance()) {
            System.out.println("Insufficient balance $" + amount);
            return false;
        }

        loggedAccount.setBalance(loggedAccount.getBalance() - amount);
        return true;
    }

    public boolean transfer(String accountNumber, int amount) {
        Account account = findOne(accountNumber);
        if (account.getAccountNumber() == "") {
            System.out.println("Invalid account");
            return false;
        }

        if (amount > loggedAccount.getBalance()) {
            System.out.println("Insufficient balance $" + amount);
            return false;
        }

        loggedAccount.setBalance(loggedAccount.getBalance() - amount);
        account.setBalance(account.getBalance() + amount);
        return true;
    }
}
