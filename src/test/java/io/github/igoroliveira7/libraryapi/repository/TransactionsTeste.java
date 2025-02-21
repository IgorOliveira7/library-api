package io.github.igoroliveira7.libraryapi.repository;


import io.github.igoroliveira7.libraryapi.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransactionsTeste {

    @Autowired
    TransactionService transactionService;

    @Test
    void SimpleTransaction() {
        transactionService.execute();
    }

    @Test
    void TransactionStageManaged() {
        transactionService.updateWithoutUpdate();
    }
}
