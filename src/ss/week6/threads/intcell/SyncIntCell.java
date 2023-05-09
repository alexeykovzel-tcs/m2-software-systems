package ss.week6.threads.intcell;

public class SyncIntCell implements IntCell {
    private boolean hasValue = false;
    private int value;

    @Override
    public synchronized void setValue(int value) {
        while (hasValue) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.value = value;
        hasValue = true;
        notifyAll();
    }

    @Override
    public synchronized int getValue() {
        while (!hasValue) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            return value;
        } finally {
            hasValue = false;
            notifyAll();
        }
    }
}
