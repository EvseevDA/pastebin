package com.github.evseevda.pastebin.hashgenerator.util.lock;

import java.util.function.Supplier;

public interface LockUtils {

    void lockAndExecuteIf(Supplier<Boolean> lockCondition, Runnable action);

}
