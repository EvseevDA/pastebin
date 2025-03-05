package com.github.evseevda.pastebin.hashgenerator.hashseed.service;

import com.github.evseevda.pastebin.hashgenerator.util.db.DatabaseOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabaseSequenceHashSeedService implements HashSeedService {

    private final DatabaseOperations databaseOperations;

    @Value("${spring.datasource.hash-seed-sequence-name}")
    private String hashSeedSequenceName;

    @Override
    public List<Integer> getNextSeeds(int n) {
        return databaseOperations.getNIntegersFromSequence(n, hashSeedSequenceName);
    }
}
