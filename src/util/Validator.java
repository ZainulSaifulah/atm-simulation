package util;

import java.util.Scanner;

public class Validator {
    public static String scannerXDigit(Scanner scanner, int digit, String message) {
        String key;
        while (true) {
            key = scanner.next();

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

}
