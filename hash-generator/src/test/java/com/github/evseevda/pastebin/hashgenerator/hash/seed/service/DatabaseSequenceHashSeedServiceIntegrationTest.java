package com.github.evseevda.pastebin.hashgenerator.hash.seed.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql("/sql/hash-seed-sequence-test.sql")
class DatabaseSequenceHashSeedServiceIntegrationTest {

    @Autowired
    private DatabaseSequenceHashSeedService hashSeedService;

    @Test
    void givenNumberOfNeededSeeds_WhenRequested_ThenCountOfReturnedSeedsIsSame() {
        // arrange
        int count = 1000;

        // action
        List<Integer> seeds = hashSeedService.getNextSeeds(count);

        // assertion
        assertEquals(count, seeds.size());
    }

    @Test
    void givenNumberOfNeededSeeds_WhenSeedsGot_ThenSeedsIsUnique() {
        // arrange
        int count = 1_000_000;

        // action
        List<Integer> seedsGot = hashSeedService.getNextSeeds(count);
        Set<Integer> uniqueSeeds = new HashSet<>(seedsGot);

        // assertion
        assertEquals(uniqueSeeds.size(), seedsGot.size());
    }

}