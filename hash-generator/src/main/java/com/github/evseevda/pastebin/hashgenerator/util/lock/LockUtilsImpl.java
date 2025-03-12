package com.github.evseevda.pastebin.hashgenerator.util.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;


public class LockUtilsImpl implements LockUtils {

    private final Lock lock = new ReentrantLock();

    @Override
    public void lockAndExecuteIf(Supplier<Boolean> lockCondition, Runnable action) {
        if (!lockCondition.get()) {
            return;
        }

        lock.lock();
        try {
            if (lockCondition.get()) {
                action.run();
            }
        } finally {
            lock.unlock();
        }
    }

}
