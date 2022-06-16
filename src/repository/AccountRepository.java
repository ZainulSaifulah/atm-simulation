package repository;

import entity.Account;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class AccountRepository {
    private final String pathLocation;

    public AccountRepository(String pathLocation) {
        this.pathLocation = pathLocation;
    }

    public List<Account> findAll() {
        Path path = Paths.get(pathLocation);
        try {
            return Files.readAllLines(path, UTF_8)
                    .stream()
                    .skip(1)
                    .map(text -> text.split(","))
                    .map(text -> new Account(text[1], text[2], Integer.parseInt(text[3]), text[0]))
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
