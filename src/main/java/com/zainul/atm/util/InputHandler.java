package com.zainul.atm.util;

import java.util.InputMismatchException;
import java.util.Scanner;

import static com.zainul.atm.util.Validator.*;

public class InputHandler {
    public static final String ACCOUNT_NUMBER = "ACCOUNT_NUMBER";
    public static final String PIN = "PIN";
    public static final String OPTION = "OPTION";
    public static final String AMOUNT = "AMOUNT";
    public static final String TRANSFER_ACCOUNT = "TRANSFER_ACCOUNT";
    public static final String TRANSFER_REFERENCE = "TRANSFER_REFERENCE";
    public static final String TRANSFER_AMOUNT = "TRANSFER_AMOUNT";
    private static final Scanner scanner = new Scanner(System.in);

    public static String scanner(String type, int min, int max, String message) {
        String input;

        do {
            System.out.print(message);
            input = scanner.nextLine();

            switch (type) {
                case ACCOUNT_NUMBER -> input = scannerAccountNumber(input, max);
                case PIN -> input = scannerPin(input, max);
                case OPTION -> input = scannerOption(input, min, max);
                case AMOUNT -> input = scannerAmount(input, min, max);
                case TRANSFER_ACCOUNT -> input = scannerTransferAccount(input, max);
                case TRANSFER_REFERENCE -> input = scannerTransferReference(input, max);
                case TRANSFER_AMOUNT -> input = scannerTransferAmount(input, min, max);
            }
        } while (input == null);

        return input;
    }

    private static String scannerAccountNumber(String text, int digit) {
        return scannerLogin(text, digit, "Account Number");
    }

    private static String scannerPin(String text, int digit) {
        return scannerLogin(text, digit, "PIN");
    }

    private static String scannerLogin(String text, int digit, String variable) {
        if (isNotNumbers(text)) {
            System.out.println(variable + " should only contains numbers");
        } else if (text.length() != digit) {
            System.out.println(variable + " should have " + digit + " digits length");
        } else {
            return text;
        }

        return null;
    }

    private static String scannerOption(String text, int start, int end) {
        if (text.isEmpty()) {
            return String.valueOf(end);
        } else if (isNotNumbers(text) || isBelow(text, start) || isAbove(text, end)) {
            System.out.println("Invalid option");
        } else {
            return text;
        }

        return null;
    }

    private static String scannerAmount(String text, int min, int max) {
        if (isNotNumbers(text) || Integer.parseInt(text) < min || Integer.parseInt(text) % 10 != 0) {
            throw new InputMismatchException("Invalid amount");
        } else if (Integer.parseInt(text) > max) {
            System.out.println("Maximum amount to withdraw is $" + max);
        } else {
            return text;
        }

        return null;
    }

    private static String scannerTransferAccount(String text, int digit) {
        if (isNotNumbers(text) || text.length() != digit) {
            System.out.println("Invalid account");
        } else {
            return text;
        }

        return null;
    }

    private static String scannerTransferReference(String text, int digit) {
        if (text.isEmpty()) {
            return generateReferenceNumber();
        } else if (isNotNumbers(text) || text.length() != digit) {
            System.out.println("Invalid Reference Number");
        } else {
            return text;
        }

        return null;
    }

    private static String scannerTransferAmount(String text, int min, int max) {
        if (isNotNumbers(text) || isNotMultiplyOfTen(text)) {
            System.out.println("Invalid amount");
        } else if (isBelow(text, min)) {
            System.out.println("Minimum amount to transfer is $" + min);
        } else if (isAbove(text, max)) {
            System.out.println("Maximum amount to transfer is $" + max);
        } else {
            return text;
        }

        return null;
    }

}
