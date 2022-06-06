package data;

import entity.Account;

import java.util.Arrays;
import java.util.List;

public class DummyAccountData {
    public List<Account> getData() {
        return Arrays.asList(
                new Account("John Doe", "012108", 100, "112233"),
                new Account("Jane Doe", "932012", 30, "112244")
        );
    }
}
