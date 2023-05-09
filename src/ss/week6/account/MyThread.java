package ss.week6.account;

public class MyThread implements Runnable {
    private final double amount;
    private final int times;
    private final Account account;

    public MyThread(double amount, int times, Account account) {
        this.amount = amount;
        this.times = times;
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            account.transaction(amount);
        }
    }
}
