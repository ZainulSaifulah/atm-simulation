package repository;

import entity.SourceType;
import entity.Transaction;
import entity.TransactionType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class TransactionRepository {

    public List<Transaction> findAll() {
        Path path = Paths.get("./transaction.csv");
        try {
            return Files.readAllLines(path, UTF_8)
                    .stream()
                    .skip(1)
                    .map(text -> text.split(","))
                    .map(text -> new Transaction(Long.valueOf(text[0]), TransactionType.valueOf(text[1]), SourceType.valueOf(text[2]), Integer.parseInt(text[3]), text[4], LocalDateTime.parse(text[5])))
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
