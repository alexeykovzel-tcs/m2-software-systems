package ss.week6.threads.console;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestSyncConsole implements Runnable {
    private final Lock lock = new ReentrantLock();

    /*private synchronized void sum() {
        String threadName = Thread.currentThread().getName();
        int a = SyncConsole.readInt(threadName + ": get number 1? ");
        int b = SyncConsole.readInt(threadName + ": get number 2? ");
        SyncConsole.println(String.format("%s: %d + %d = %d", threadName, a, b, a + b));
    }*/

    private void sum() {
        String threadName = Thread.currentThread().getName();
        lock.lock();
        try {
            int a = SyncConsole.readInt(threadName + ": get number 1? ");
            int b = SyncConsole.readInt(threadName + ": get number 2? ");
            SyncConsole.println(String.format("%s: %d + %d = %d", threadName, a, b, a + b));
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        sum();
    }

    public static void main(String[] args) {
        TestSyncConsole console = new TestSyncConsole();
        new Thread(console, "Thread A").start();
        new Thread(console, "Thread B").start();
    }
}
