package com.github.evseevda.pastebin.hashgenerator.hash.generator.service;

import com.github.evseevda.pastebin.hashgenerator.hash.converter.ToHashConverter;
import com.github.evseevda.pastebin.hashgenerator.hash.seed.service.HashSeedService;
import com.github.evseevda.pastebin.hashgenerator.redis.service.RedisService;
import com.github.evseevda.pastebin.hashgenerator.util.lock.LockUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HashGeneratorServiceImplTest {

    @Mock
    private ToHashConverter<Integer> toHashConverter;

    @Mock
    private HashSeedService hashSeedService;

    @Mock
    private RedisService redisService;

    @Mock
    private LockUtils lockUtils;

    @InjectMocks
    private HashGeneratorServiceImpl hashGeneratorService;

    @Test
    void givenHashGenerator_WhenGenerateCashIsCalled_ThenLockUtilsLockAndExecuteIfAndRedisServiceGetHashAndRemoveIsCalled() {
        // arrange
        doNothing().when(lockUtils).lockAndExecuteIf(any(), any());
        when(redisService.getHashAndRemove()).thenReturn("");

        // action
        hashGeneratorService.generateHash();

        // assertion
        verify(lockUtils).lockAndExecuteIf(any(), any());
        verify(redisService).getHashAndRemove();
    }

}