package repository;

import entity.SourceType;
import entity.Transaction;
import entity.TransactionType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class TransactionRepository {
    private final Path path = Paths.get("./transaction.csv");

    public List<Transaction> findAll() {
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

    public Transaction create(Transaction transaction) {
        try {
            transaction.setId(getId());
            Files.writeString(
                    path,
                    String.format("%s%s,%s,%s,%s,%s,%s",
                            System.lineSeparator(),
                            transaction.getId(),
                            transaction.getTransactionType(),
                            transaction.getSourceType(),
                            transaction.getAmount(),
                            transaction.getAccountNumber(),
                            transaction.getCreatedAt()
                    ),
                    UTF_8, StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return transaction;
    }

    private Long getId() {
        return findAll().size() + 1L;
    }

}
