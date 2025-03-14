package com.github.evseevda.pastebin.hashgenerator.redis.service;

import com.github.evseevda.pastebin.hashgenerator.config.IntegrationTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Import(IntegrationTestConfig.class)
@ActiveProfiles("test")
class RedisServiceImplTest {

    @Autowired
    private RedisServiceImpl redisService;

    @BeforeEach
    void clearCache() {
        while (!redisService.hashesIsEmpty()) {
            redisService.getHashAndRemove();
        }
    }

    @Test
    void givenEmptyCache_WhenCheckingForEmpty_ThenReturnsTrue() {
        assertTrue(redisService.hashesIsEmpty());
    }

    @Test
    void givenNotEmptyCache_WhenCheckingForEmpty_ThenReturnsFalse() {
        // arrange
        redisService.saveHashes(List.of("1"));

        // assertion
        assertFalse(redisService.hashesIsEmpty());
    }

    @Test
    void givenNotEmptyCache_WhenAllElementsIsGot_ThenCacheIsEmptyAndGottenElementsAreNotModified() {
        // arrange
        List<String> srcElements = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        redisService.saveHashes(srcElements);

        // action
        List<String> gottenElements = new ArrayList<>();
        for (int i = 0; i < srcElements.size(); i++) {
            gottenElements.add(redisService.getHashAndRemove());
        }

        // assertion
        assertTrue(redisService.hashesIsEmpty());
        assertEquals(srcElements, gottenElements);
    }


}