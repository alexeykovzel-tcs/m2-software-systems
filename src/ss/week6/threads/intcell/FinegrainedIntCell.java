package ss.week6.threads.intcell;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FinegrainedIntCell implements IntCell {
    private final Lock lock = new ReentrantLock();
    private final Condition bufferNotEmpty = lock.newCondition();
    private final Condition bufferNotFull = lock.newCondition();
    private Integer value;

    @Override
    public void setValue(int value) {
        lock.lock();
        try {
            while (this.value != null)
                bufferNotFull.await();
            this.value = value;
            bufferNotEmpty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int getValue() {
        lock.lock();
        try {
            while (value == null)
                bufferNotEmpty.await();
            return value;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return 0;
        } finally {
            value = null;
            bufferNotFull.signalAll();
            lock.unlock();
        }
    }
}
