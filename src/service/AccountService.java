package service;

import entity.Account;
import repository.AccountRepository;

import java.util.List;

public class AccountService {
    private final AccountRepository accountRepository;
    private Account loggedAccount;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
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

    public Account update(Account account) {
        return accountRepository.update(account);
    }
}
