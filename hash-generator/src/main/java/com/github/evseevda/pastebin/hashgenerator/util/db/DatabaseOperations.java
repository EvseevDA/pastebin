package com.github.evseevda.pastebin.hashgenerator.util.db;

import java.util.List;

public interface DatabaseOperations {

    List<Integer> getNIntegersFromSequence(int n, String sequenceName);

}
