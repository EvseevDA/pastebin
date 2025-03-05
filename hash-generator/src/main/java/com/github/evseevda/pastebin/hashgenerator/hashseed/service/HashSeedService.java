package com.github.evseevda.pastebin.hashgenerator.hashseed.service;

import java.util.List;

public interface HashSeedService {

    List<Integer> getNextSeeds(int n);

}
