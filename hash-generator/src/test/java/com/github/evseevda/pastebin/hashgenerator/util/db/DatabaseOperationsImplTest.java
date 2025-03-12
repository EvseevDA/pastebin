package com.github.evseevda.pastebin.hashgenerator.util.db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@Sql("/sql/hash-seed-sequence-test.sql")
class DatabaseOperationsImplTest {

    @Autowired
    private DatabaseOperationsImpl databaseOperations;

    @Value("${spring.datasource.hash-seed-sequence-name}")
    private String sequenceName;

    @Test
    void givenNumberOfNeededIntegers_WhenRequested_ThenCountOfReturnedIntegersIsSame() {
        // arrange
        int expectedIntegers = 1_000_000;

        // action
        List<Integer> integersGot = databaseOperations.getNIntegersFromSequence(expectedIntegers, sequenceName);

        // assertion
        assertEquals(expectedIntegers, integersGot.size());
    }

    @Test
    void givenExpectedInteger_WhenRequested_ThenReturnedIntegerIsSame_T1() {
        // arrange
        int expected = 1;

        // action
        List<Integer> integersGot = databaseOperations.getNIntegersFromSequence(1, sequenceName);

        // assertion
        assertEquals(expected, integersGot.get(0));
    }

    @Test
    void givenExpectedInteger_WhenRequested_ThenReturnedIntegerIsSame_T2() {
        // arrange
        int skip = 100_000;
        int expected = 100_001;

        // action
        databaseOperations.getNIntegersFromSequence(skip, sequenceName);
        List<Integer> integersGot = databaseOperations.getNIntegersFromSequence(1, sequenceName);

        // assertion
        assertEquals(expected, integersGot.get(0));
    }

}