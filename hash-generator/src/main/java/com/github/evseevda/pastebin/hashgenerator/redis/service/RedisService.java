package com.github.evseevda.pastebin.hashgenerator.redis.service;

import java.util.List;

public interface RedisService {

    boolean hashesIsEmpty();
    void saveHashes(List<String> hashes);
    String getHashAndRemove();

}
