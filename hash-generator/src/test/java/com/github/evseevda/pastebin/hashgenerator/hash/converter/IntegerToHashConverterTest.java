package com.github.evseevda.pastebin.hashgenerator.hash.converter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class IntegerToHashConverterTest {

    private static final int REQUIRED_HASH_LENGTH = 16;

    private static IntegerToHashConverter CONVERTER;

    @BeforeAll
    static void setUpForAll() {
        CONVERTER = new IntegerToHashConverter();
    }

    @Test
    void givenSmallSeed_WhenHashIsGenerated_ThenHashIsShort() {
        // arrange
        int seed = 1;

        // action
        String hash = CONVERTER.convert(seed);

        // assertion
        assertTrue(hash.length() <= REQUIRED_HASH_LENGTH);
    }

    @Test
    void givenBigSeed_WhenHashIsGenerated_ThenHashIsShort() {
        // arrange
        int seed = Integer.MAX_VALUE;

        // action
        String hash = CONVERTER.convert(seed);

        // assertion
        assertTrue(hash.length() <= REQUIRED_HASH_LENGTH);
    }

    @Test
    void givenBigCountOfHashes_WhenHashesIsGenerated_ThenHashesIsUnique() {
        // arrange
        int count = 1_000_000;

        // action
        int mid = Integer.MAX_VALUE / 2;
        List<String> generatedHashes = new ArrayList<>();
        for (int i = mid; i < mid + count; i++) {
            generatedHashes.add(CONVERTER.convert(i));
        }
        Set<String> uniqueHashes = new HashSet<>(generatedHashes);

        // assertion
        assertEquals(uniqueHashes.size(), generatedHashes.size());
    }

}