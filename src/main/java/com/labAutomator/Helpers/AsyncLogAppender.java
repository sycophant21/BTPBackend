package com.labAutomator.Helpers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class AsyncLogAppender extends Thread{
    private static LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(1000);
    private static Object lock = new Object();

    public AsyncLogAppender() {
        super(queuePoller(queue));
        setDaemon(true);
        start();
    }

    private static Runnable queuePoller(LinkedBlockingQueue<Runnable> queue) {
        class Poller implements Runnable {
            public void run() {
                while (true) {
                    try {
                        Runnable result = queue.poll(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
                        if (result != null) {
                            result.run();
                        }
                        notifyShutDownHook();
                    }
                    catch (Throwable e) {
                        System.out.println("Appender Stopped");
                        e.printStackTrace();
                    }
                }
            }
            private void notifyShutDownHook() {
                if (queue.size() == 0) {
                    synchronized (lock) {
                        lock.notify();
                    }
                 }
            }
        }
        return new Poller();
    }
    public void flush() {
        final CountDownLatch latch = new CountDownLatch(1);
        write(new Runnable() {
            public void run() {
                latch.countDown();
            }
        });
        try {
            latch.await();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void write(Runnable runnable) {
        try {
            queue.put(runnable);
        }
        catch (InterruptedException e) {
            System.out.println("failed");
            e.printStackTrace();
        }
    }
}
