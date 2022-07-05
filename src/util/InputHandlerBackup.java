package util;

import java.util.Scanner;

import static util.Validator.*;

public class InputHandlerBackup {

    private static final Scanner scanner = new Scanner(System.in);

    public static String scannerLogin(int digit, String message) {
        String key;
        while (true) {
            System.out.print("Enter " + message + ": ");
            key = scanner.nextLine();

            if (isNotNumbers(key)) {
                System.out.println(message + " should only contains numbers");
            } else if (key.length() != digit) {
                System.out.println(message + " should have " + digit + " digits length");
            } else {
                break;
            }
        }

        return key;
    }

    public static String scannerOption(int start, int end, String message) {
        String key;
        while (true) {
            System.out.print(message);
            key = scanner.nextLine();

            if (key.isEmpty()) {
                key = String.valueOf(end);
                break;
            } else if (isNotNumbers(key) || isBelow(key, start) || isAbove(key, end)) {
                System.out.println("Invalid option");
            } else {
                break;
            }
        }

        return key;
    }

    public static String scannerAmount(int min, int max, String message) {
        String key;
        while (true) {
            System.out.print(message);
            key = scanner.nextLine();

            if (isNotNumbers(key) || Integer.parseInt(key) < min || Integer.parseInt(key) % 10 != 0) {
                System.out.println("Invalid amount");
            } else if (Integer.parseInt(key) > max) {
                System.out.println("Maximum amount to withdraw is $" + max);
            } else {
                break;
            }

        }

        return key;
    }

    public static String scannerTransferAccount(int digit, String message) {
        String key;
        while (true) {
            System.out.print(message);
            key = scanner.nextLine();

            if (isNotNumbers(key) || key.length() != digit) {
                System.out.println("Invalid account");
            } else {
                break;
            }
        }

        return key;
    }

    public static String scannerTransferReference(int digit, String message) {
        String key;
        while (true) {
            System.out.print(message);
            key = scanner.nextLine();

            if (key.isEmpty()) {
                key = generateReferenceNumber();
                break;
            } else if (isNotNumbers(key) || key.length() != digit) {
                System.out.println("Invalid Reference Number");
            } else {
                break;
            }
        }

        return key;
    }

    public static String scannerTransferAmount(int min, int max, String message) {
        String key;
        while (true) {
            System.out.print(message);
            key = scanner.nextLine();

            if (isNotNumbers(key) || isNotMultiplyOfTen(key)) {
                System.out.println("Invalid amount");
            } else if (isBelow(key, min)) {
                System.out.println("Minimum amount to transfer is $" + min);
            } else if (isAbove(key, max)) {
                System.out.println("Maximum amount to transfer is $" + max);
            } else {
                break;
            }
        }

        return key;
    }

}
