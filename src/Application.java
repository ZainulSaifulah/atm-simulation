import data.DummyAccountData;
import service.AccountService;

import java.util.Scanner;

import static util.Validator.*;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccountService accountService = new AccountService(new DummyAccountData());

        while (true) {
            welcomeScreen(scanner, accountService);

            break;
        }

        System.out.println(accountService.getLoggedAccount());
    }

    public static void welcomeScreen(Scanner scanner, AccountService accountService) {
        while (true) {
            System.out.print("Enter Account Number: ");
            String accountNumber = scannerXDigit(scanner, 6, "Account Number");
            System.out.print("Enter PIN: ");
            String pin = scannerXDigit(scanner, 6, "PIN");

            if (accountService.login(accountNumber, pin)) {
                break;
            }

            System.out.println("Invalid Account Number/PIN");
        }
    }
}
