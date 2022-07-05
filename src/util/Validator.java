package util;

import java.util.Random;

public class Validator {
    public static String generateReferenceNumber() {
        StringBuilder referenceNumber = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            referenceNumber.append(new Random().nextInt(9));
        }
        return referenceNumber.toString();
    }

    public static boolean isNotNumbers(String text) {
        return !text.matches("\\d+");
    }

   public static boolean isNotMultiplyOfTen(String text) {
        return Integer.parseInt(text) % 10 != 0;
    }

    public static boolean isBelow(String text, int min) {
        return Integer.parseInt(text) < min;
    }

    public static boolean isAbove(String text, int max) {
        return Integer.parseInt(text) > max;
    }

}
