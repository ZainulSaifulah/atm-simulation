package util;

import java.util.Random;
import java.util.Scanner;

public class Validator {
    public static String scannerLogin(Scanner scanner, int digit, String message) {
        String key;
        while (true) {
            key = scanner.nextLine();

            if (!key.matches("[0-9]+")) {
                System.out.println(message + " should only contains numbers");
            } else if (key.length() != digit) {
                System.out.println(message + " should have " + digit + " digits length");
            } else {
                break;
            }
        }

        return key;
    }

    public static String scannerOption(Scanner scanner, int start, int end, String message) {
        String key;
        while (true) {
            key = scanner.nextLine();

            if (key.isEmpty()) {
                key = String.valueOf(end);
                break;
            } else if (!key.matches("[0-9]+") || Integer.parseInt(key) < start || Integer.parseInt(key) > end) {
                System.out.println("Invalid option");
                System.out.print(message);
            } else {
                break;
            }
        }

        return key;
    }

    public static String scannerAmount(Scanner scanner, int min, int max, String message) {
        String key;
        while (true) {
            key = scanner.nextLine();

            if (!key.matches("[0-9]+") || Integer.parseInt(key) < min || Integer.parseInt(key) % 10 != 0) {
                System.out.println("Invalid amount");
                System.out.print(message);
            } else if (Integer.parseInt(key) > max) {
                System.out.println("Maximum amount to withdraw is $" + max);
                System.out.print(message);
            } else {
                break;
            }

        }

        return key;
    }

    public static String scannerTransferAccount(Scanner scanner, int digit, String message) {
        String key;
        while (true) {
            key = scanner.nextLine();

            if (!key.matches("[0-9]+") || key.length() != digit) {
                System.out.println("Invalid account");
                System.out.print(message);
            } else {
                break;
            }
        }

        return key;
    }

    public static String scannerTransferReference(Scanner scanner, int digit, String message) {
        String key;
        while (true) {
            key = scanner.nextLine();

            if (key.isEmpty()) {
                key = generateReferenceNumber();
                break;
            } else if (!key.matches("[0-9]+") || key.length() != digit) {
                System.out.println("Invalid Reference Number");
                System.out.print(message);
            } else {
                break;
            }
        }

        return key;
    }

    public static String scannerTransferAmount(Scanner scanner, int min, int max, String message) {
        String key;
        while (true) {
            key = scanner.nextLine();

            if (!key.matches("[0-9]+") || Integer.parseInt(key) % 10 != 0) {
                System.out.println("Invalid amount");
                System.out.print(message);
            } else if (Integer.parseInt(key) < min) {
                System.out.println("Minimum amount to transfer is $" + min);
                System.out.print(message);
            } else if (Integer.parseInt(key) > max) {
                System.out.println("Maximum amount to transfer is $" + max);
                System.out.print(message);
            } else {
                break;
            }
        }

        return key;
    }

    private static String generateReferenceNumber() {
        String referenceNumber = "";
        for (int i = 0; i < 6; i++) {
            referenceNumber += new Random().nextInt(9);
        }
        return referenceNumber;
    }


}
