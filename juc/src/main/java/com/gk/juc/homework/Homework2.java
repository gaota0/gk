package com.gk.juc.homework;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.LockSupport;

/**
 * @author gaot
 * @date 2021/11/27
 */
@Slf4j
public class Homework2 {
    private static final Object LOCK = new Object();
    private static ThreadPoolExecutor executor =
        new ThreadPoolExecutor(8, 8, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    private static long result = -1;

    public static void main(String[] args) throws Exception {
        whileDemo();

        subThreadJoin();

        synchronizedSignal();

        blockQueue();

        threadPool();

        countDownLatch();

        cyclicBarrier();

        lockSupport();

        completeFuture();

        executor.shutdown();
    }

    private static void whileDemo() throws ExecutionException, InterruptedException {
        final Future<Long> future = executor.submit(() -> {
            result = Calculator.fibonacci(3);
            return result;
        });
        while (result < 0) {

        }
        log.info("while.result,{}", future.get());
    }

    private static void lockSupport() throws ExecutionException, InterruptedException {
        final Thread main = Thread.currentThread();
        final Future<Long> future = executor.submit(() -> {
            final long result = Calculator.fibonacci(3);
            LockSupport.unpark(main);
            return result;
        });
        LockSupport.park();
        final Long result = future.get();
        log.info("lockSupport.result,{}", result);
    }

    private static void cyclicBarrier() throws ExecutionException, InterruptedException {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(1);
        final Future<Long> future = executor.submit(() -> {
            final long result = Calculator.fibonacci(3);
            cyclicBarrier.await();
            return result;
        });
        final Long result = future.get();
        log.info("CyclicBarrier.result,{}", result);
    }

    private static void countDownLatch() throws InterruptedException, ExecutionException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final Future<Long> future = executor.submit(() -> {
            final long result = Calculator.fibonacci(3);
            countDownLatch.countDown();
            return result;
        });
        countDownLatch.await();
        final Long result = future.get();
        log.info("countDownLatch.result,{}", result);
    }

    private static void blockQueue() throws InterruptedException {
        final ArrayBlockingQueue<Long> queue = new ArrayBlockingQueue<>(1);
        new Thread(() -> {
            final long result = Calculator.fibonacci(3);
            try {
                queue.put(result);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
        final Long result = queue.take();
        log.info("blockQueue.result,{}", result);

    }

    private static void threadPool() {
        final Future<Long> future = executor.submit(() -> Calculator.fibonacci(3));
        try {
            final Long result = future.get();
            log.info("threadPool.result,{}", result);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

    }

    public static void synchronizedSignal() throws InterruptedException {
        final RunnableSyncImpl runnable = new RunnableSyncImpl(3);
        final Thread subThread = new Thread(runnable);
        subThread.start();
        synchronized (LOCK) {
            LOCK.wait();
        }

        log.info("synchronizedSignal.result, {} ", runnable.getResult());

    }

    public static void subThreadJoin() throws InterruptedException {
        final RunnableImpl runnable = new RunnableImpl(3);
        final Thread subThread = new Thread(runnable);
        subThread.start();
        subThread.join();
        final long result = runnable.getResult();
        log.info("subThreadJoin.result, {} ", result);
    }

    private static void completeFuture() throws ExecutionException, InterruptedException {
        AtomicLong result = new AtomicLong();
        final CompletableFuture<Long> completableFuture = CompletableFuture.supplyAsync(() -> {
            result.set(Calculator.fibonacci(3));
            return result.get();
        });
        CompletableFuture.allOf(completableFuture).get();
        log.info("completeFuture.result,{}", result);
    }

    @Getter
    public static class RunnableImpl implements Runnable {
        private long result;
        private final int index;

        public RunnableImpl(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            this.result = Calculator.fibonacci(index);
        }
    }

    @Getter
    public static class RunnableSyncImpl implements Runnable {
        private long result;
        private final int index;

        public RunnableSyncImpl(int index) {
            this.index = index;
        }

        @SneakyThrows
        @Override
        public void run() {
            synchronized (LOCK) {
                this.result = Calculator.fibonacci(index);
                LOCK.notify();
            }
        }
    }
}
