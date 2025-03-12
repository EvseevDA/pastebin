package com.github.evseevda.pastebin.hashgenerator.util.lock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LockUtilsImplTest {

    private static class Foo {
        int a;
        int modCount = 0;

        boolean aIsNotInitialized() {
            return a == 0;
        }

        void initializeA() {
            this.a = 1;
            modCount++;
        }
    }

    private LockUtilsImpl lockUtils = new LockUtilsImpl();
    private Foo foo;

    @BeforeEach
    void initForEach() {
        foo = new Foo();
    }

    @Test
    void givenObjectsWhichTryingToInitA_WhenFirstInitializedA_ThenOtherIgnored() throws Exception {
        // arrange
        Runnable task = () -> lockUtils.lockAndExecuteIf(
                foo::aIsNotInitialized,
                () -> foo.initializeA()
        );
        int threads = 1000;
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        int expectedModCount = 1;

        // action
        for (int i = 0; i < threads; i++) {
            executor.submit(task);
        }
        executor.shutdown();
        boolean terminated = executor.awaitTermination(10L, TimeUnit.SECONDS);

        // assertion
        assertTrue(terminated);
        assertEquals(expectedModCount, foo.modCount);
    }

}