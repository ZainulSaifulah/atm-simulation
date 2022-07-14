package com.zainul.atm.service;

import com.zainul.atm.entity.Account;

public class LoggedService {
    private final AccountService accountService = new AccountService();
    private final TransactionService transactionService = new TransactionService();
    private Account loggedAccount;

    public boolean login(String accountNumber, String pin) {
        Account account = accountService.findOne(accountNumber);
        return account != null && account.getPin().equals(pin) && setLoggedAccount(account);
    }

    private boolean setLoggedAccount(Account account) {
        this.loggedAccount = account;
        return true;
    }

    public Account getLoggedAccount() {
        return this.loggedAccount;
    }

    public boolean withdraw(int amount) {
        return transactionService.withdraw(loggedAccount.getAccountNumber(), amount);
    }

    public boolean transfer(String receiverAccountNumber, int amount) {
        return transactionService.transfer(loggedAccount.getAccountNumber(), receiverAccountNumber, amount);
    }
}