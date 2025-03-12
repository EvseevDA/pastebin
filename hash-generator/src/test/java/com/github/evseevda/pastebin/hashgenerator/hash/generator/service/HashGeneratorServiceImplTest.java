package com.github.evseevda.pastebin.hashgenerator.hash.generator.service;

import com.github.evseevda.pastebin.hashgenerator.config.CommonTestConfig;
import com.redis.testcontainers.RedisContainer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(classes = CommonTestConfig.class)
class HashGeneratorServiceImplTest {

    @Autowired
    private RedisContainer redisContainer;

    @Test
    void givenRedisContainer_WhenCheckingRunningStatus_ThenStatusIsRunning() {
        assertTrue(redisContainer.isRunning());
    }

}