package com.github.evseevda.pastebin.hashgenerator.hash.seed.service;

import java.util.List;

public interface HashSeedService {

    List<Integer> getNextSeeds(int count);

}
