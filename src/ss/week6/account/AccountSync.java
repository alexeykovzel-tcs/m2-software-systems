package ss.week6.account;

public class AccountSync {
    public static void main(String[] args) {
        Account account = new Account();
        Thread t1 = new Thread(new MyThread(-10.0, 1000, account));
        Thread t2 = new Thread(new MyThread(10.0, 1000, account));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(account.getBalance());
    }
}
