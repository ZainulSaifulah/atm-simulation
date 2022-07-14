package com.zainul.atm.service;

import com.zainul.atm.entity.Account;
import com.zainul.atm.repository.AccountRepository;

import java.util.List;

import static com.zainul.atm.util.Configuration.filePath;

public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService() {
        this.accountRepository = new AccountRepository(filePath);
    }
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account findOne(String accountNumber) {
        for (Account account : accountRepository.findAll()) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public Account update(Account account) {
        return accountRepository.update(account);
    }
}
