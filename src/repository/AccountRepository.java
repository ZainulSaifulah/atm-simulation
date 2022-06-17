package repository;

import entity.Account;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.nio.charset.StandardCharsets.UTF_8;

public class AccountRepository {
    private final String pathLocation;

    public AccountRepository(String pathLocation) {
        this.pathLocation = pathLocation;
    }

    public List<Account> findAll() {
        Path path = Paths.get(pathLocation);
        try {
            List<String> files = Files.readAllLines(path, UTF_8);
            String duplicateAccountNumber = findDuplicateAccountNumber(files);
            String duplicateRecord = findDuplicateRecord(files);
            if (!duplicateAccountNumber.isEmpty()) {
                System.out.println("There can't be 2 different accounts with the same Account Number " + duplicateAccountNumber);
                System.exit(0);
            }

            if (!duplicateRecord.isEmpty()) {
                System.out.println("There can't be duplicated records " + duplicateRecord);
                System.exit(0);
            }

            return files
                    .stream()
                    .skip(1)
                    .map(text -> text.split(","))
                    .map(text -> new Account(text[1], text[2], Integer.parseInt(text[3]), text[0]))
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String findDuplicateRecord(List<String> files) {
        var result = files
                .stream()
                .skip(1)
                .filter(text -> Collections.frequency(files, text) > 1)
                .toList();

        return result.size() > 0 ? result.get(0) : "";
    }

    private String findDuplicateAccountNumber(List<String> files) {
        Set<String> accountNumbers = new HashSet<>();

        var result = files
                .stream()
                .skip(1)
                .map(text -> text.split(","))
                .filter(text -> !accountNumbers.add(text[0]))
                .map(text -> text[0])
                .toList();

        return result.size() > 0 ? result.get(0) : "";
    }
}
