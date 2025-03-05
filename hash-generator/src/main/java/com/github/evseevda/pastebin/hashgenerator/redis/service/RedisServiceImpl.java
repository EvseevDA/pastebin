package com.github.evseevda.pastebin.hashgenerator.redis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    @Value("${hash.properties.cache.redis-key-name}")
    private String hashesKeyName;

    @Override
    public boolean hashesIsEmpty() {
        Long size = listOps().size(hashesKeyName);
        return size == null || size == 0L;
    }

    @Override
    public void saveHashes(List<String> hashes) {
        hashes.forEach(this::saveHash);
    }

    private void saveHash(String hash) {
        listOps().rightPush(hashesKeyName, hash);
    }

    @Override
    public String getHashAndRemove() {
        return listOps().leftPop(hashesKeyName);
    }

    private ListOperations<String, String> listOps() {
        return redisTemplate.opsForList();
    }

}
