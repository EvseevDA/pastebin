package com.github.evseevda.pastebin.hashgenerator.hash.generator.service;

import com.github.evseevda.pastebin.hashgenerator.config.IntegrationTestConfig;
import com.github.evseevda.pastebin.hashgenerator.redis.service.RedisService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@SpringBootTest
@Import(IntegrationTestConfig.class)
@Sql("/sql/hash-seed-sequence-test.sql")
@Transactional
class HashGeneratorServiceImplIntegrationTest {

    @Autowired
    private HashGeneratorServiceImpl hashGeneratorService;

    @MockBean
    private RedisService mockedRedisService;


    @BeforeEach
    void clearCache() {
        when(mockedRedisService.getHashAndRemove()).thenReturn("");
        doNothing().when(mockedRedisService).saveHashes(anyList());
    }


    @Test
    void givenHashGeneratorService_WhenCacheIsEmpty_ThenRedisServiceSaveHashesIsCalled() {
        // arrange
        when(mockedRedisService.hashesIsEmpty()).thenReturn(true);

        // action
        hashGeneratorService.generateHash();

        // assertion
        verify(mockedRedisService).saveHashes(anyList());
    }

    @Test
    void givenHashGeneratorService_WhenCacheIsNotEmpty_ThenRedisServiceSaveHashesIsNotCalled() {
        // arrange
        when(mockedRedisService.hashesIsEmpty()).thenReturn(false);

        // action
        hashGeneratorService.generateHash();

        // assertion
        verify(mockedRedisService, never()).saveHashes(anyList());
    }

}