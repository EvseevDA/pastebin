package com.github.evseevda.pastebin.hashgenerator.hash.generator.service;

import com.github.evseevda.pastebin.hashgenerator.hash.converter.ToHashConverter;
import com.github.evseevda.pastebin.hashgenerator.hash.seed.service.HashSeedService;
import com.github.evseevda.pastebin.hashgenerator.redis.service.RedisService;
import com.github.evseevda.pastebin.hashgenerator.util.lock.LockUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HashGeneratorServiceImpl implements HashGeneratorService {

    private final HashSeedService hashSeedService;
    private final RedisService redisService;
    private final ToHashConverter<Integer> hashConverter;
    private final LockUtils prototypeLockUtils;

    @Value("${hash.properties.cache.size}")
    private int hashCacheSize;

    @Override
    public String generateHash() {
        prototypeLockUtils.lockAndExecuteIf(redisService::hashesIsEmpty, this::generateAndCacheHashes);
        return redisService.getHashAndRemove();
    }

    private void generateAndCacheHashes() {
        List<String> hashes = hashSeedService.getNextSeeds(hashCacheSize).stream()
                .map(hashConverter::convert)
                .toList();
        redisService.saveHashes(hashes);
    }

}
