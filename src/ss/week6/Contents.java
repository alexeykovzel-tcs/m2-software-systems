package ss.week6;

public class Contents {
    private int contents = 0;

    public void add(int amount) {
        contents += amount;
    }

    public int get() {
        return contents;
    }

    // possible results:
    // 1 : a1 overrides a2
    // 2 : a2 overrides a1
    // 3 : a1 and a2 are uninterrupted by each other
    public static void main(String[] args) {
        Contents cell = new Contents();
        Thread a1 = new Thread(new Adder(cell, 1));
        Thread a2 = new Thread(new Adder(cell, 2));
        a1.start();
        a2.start();

        try {
            a1.join();
            a2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(cell.get());
    }
}

class Adder implements Runnable {
    private final Contents cell;
    private final int amount;

    public Adder(Contents cellArg, int amountArg) {
        this.cell = cellArg;
        this.amount = amountArg;
    }

    public void run() {
        // 'synchronized' to ensure that the result value is always 3
        synchronized (cell) {
            cell.add(amount);
        }
    }
}
