package com.zainul.atm.controller;

import com.zainul.atm.service.LoggedService;

import static com.zainul.atm.util.InputHandler.*;
import static com.zainul.atm.view.ApplicationView.*;

public class ApplicationController {
    private final LoggedService loggedService;

    public ApplicationController() {
        this.loggedService = new LoggedService();
    }

    public void welcomeScreen() {
        String accountNumber = scanner(ACCOUNT_NUMBER, 6, 6, accountNumberMenu());
        String pin = scanner(PIN, 6, 6, pinMenu());

        if (loggedService.login(accountNumber, pin)) {
            transactionScreen();
        } else {
            System.out.println("Invalid Account Number/PIN");
            welcomeScreen();
        }
    }

    public void transactionScreen() {
        String option = scanner(OPTION, 1, 3, transactionMenu());
        switch (option) {
            case "1" -> withdrawScreen();
            case "2" -> fundTransferScreen();
            default -> welcomeScreen();
        }
    }

    public void withdrawScreen() {
        String option = scanner(OPTION, 1, 5, withdrawMenu());
        switch (option) {
            case "1" -> {
                if (loggedService.withdraw(10)) {
                    summaryScreen("10");
                }
            }
            case "2" -> {
                if (loggedService.withdraw(50)) {
                    summaryScreen("50");
                }
            }
            case "3" -> {
                if (loggedService.withdraw(100)) {
                    summaryScreen("100");
                }
            }
            case "4" -> otherWithdrawScreen();
            default -> transactionScreen();
        }
    }

    public void otherWithdrawScreen() {
        String amount = scanner(AMOUNT, 10, 1000, otherWithdrawMenu());
        if (loggedService.withdraw(Integer.parseInt(amount))) {
            summaryScreen(amount);
        }
    }

    public void fundTransferScreen() {
        try {
            String accountNumber = scanner(TRANSFER_ACCOUNT, 6, 6, fundTransferAccountMenu());
            String amount = scanner(TRANSFER_AMOUNT, 1, 1000, fundTransferAmountMenu());
            String referenceNumber = scanner(TRANSFER_REFERENCE, 6, 6, fundTransferReferenceMenu());

            String option = scanner(OPTION, 1, 2, fundTransferConfirmationMenu(accountNumber, amount, referenceNumber));
            if ("1".equals(option) && loggedService.transfer(accountNumber, Integer.parseInt(amount))) {
                summaryScreen(amount);
            } else {
                transactionScreen();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transactionScreen();
        }
    }

    public void summaryScreen(String amount) {
        String option = scanner(OPTION, 1, 2, summaryMenu(amount, String.valueOf(loggedService.getLoggedAccount().getBalance())));
        if ("1".equals(option)) {
            transactionScreen();
        } else {
            welcomeScreen();
        }
    }
}
