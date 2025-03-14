package com.github.evseevda.pastebin.hashgenerator.hash.seed.service;

import com.github.evseevda.pastebin.hashgenerator.exception.HashGeneratorServiceException;
import com.github.evseevda.pastebin.hashgenerator.util.db.DatabaseOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabaseSequenceHashSeedService implements HashSeedService {

    private final DatabaseOperations databaseOperations;

    @Value("${spring.datasource.hash-seed-sequence-name}")
    private String hashSeedSequenceName;

    @Override
    public List<Integer> getNextSeeds(int count) {
        try {
            return databaseOperations.getNIntegersFromSequence(count, hashSeedSequenceName);
        } catch (DataAccessException e) {
            throw new HashGeneratorServiceException(
                    "Error while getting values from sequence (%s)."
                            .formatted(hashSeedSequenceName),
                    e
            );
        }
    }

}
