package com.github.evseevda.pastebin.hashgenerator.util.lock;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

@Component
@Scope("prototype")
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
