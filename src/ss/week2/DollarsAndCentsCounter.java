package ss.week2;

public class DollarsAndCentsCounter {
    private int totalCents = 0;

    public void add(int dollars, int cents) {
        totalCents += dollars * 100 + cents;
    }

    public int dollars() {
        return totalCents / 100;
    }

    public int cents() {
        return totalCents - dollars() * 100;
    }

    public void reset() {
        totalCents = 0;
    }
}
