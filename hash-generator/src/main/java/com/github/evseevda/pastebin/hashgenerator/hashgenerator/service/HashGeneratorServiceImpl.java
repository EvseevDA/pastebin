package com.github.evseevda.pastebin.hashgenerator.hashgenerator.service;

import com.github.evseevda.pastebin.hashgenerator.converter.ToHashConverter;
import com.github.evseevda.pastebin.hashgenerator.redis.service.RedisService;
import com.github.evseevda.pastebin.hashgenerator.hashseed.service.HashSeedService;
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

    @Value("${hash.properties.cache.size}")
    private int hashCacheSize;

    @Override
    public String generateHash() {
        if (redisService.hashesIsEmpty()) {
            List<String> hashes = hashSeedService.getNextSeeds(hashCacheSize).stream()
                    .map(hashConverter::convert)
                    .toList();
            redisService.saveHashes(hashes);
        }
        return redisService.getHashAndRemove();
    }

}
