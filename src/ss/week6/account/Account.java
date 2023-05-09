package ss.week6.account;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private final Lock lock = new ReentrantLock();
    private final Condition funded = lock.newCondition();
    private double balance = 0.0;

    /**
     * Makes a transaction if the total balance after it is bigger than 1000.
     * Otherwise, it awaits until it is funded.
     * @param amount    transaction amount.
     */
    public void transaction(double amount) {
        lock.lock();
        try {
            while (balance + amount < -1000)
                funded.await();
            balance += amount;
            funded.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public double getBalance() {
        return balance;
    }
}
