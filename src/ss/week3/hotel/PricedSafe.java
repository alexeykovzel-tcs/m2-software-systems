package ss.week3.hotel;

import ss.week3.bill.Bill;
import ss.week3.password.Password;

public class PricedSafe extends Safe implements Bill.Item {
    private final Password password;
    private final double price;
    private final String briefName;

    public static void main(String[] args) {
        PricedSafe safe = new PricedSafe(0);
        safe.activate(null);
    }

    public PricedSafe(double price) {
        briefName = "Safe for " + price;
        password = new Password();
        this.price = price;
    }

    public void activate(String passText) {
        assert (passText != null);
        if (password.getValue().equals(passText)) {
            isActivated = true;
        }
    }

    @Override
    public void activate() {
        System.out.println("WARNING: Password required!");
    }

    public void open(String passText) {
        assert (passText != null);
        boolean verifiedPass = passText.equals(password.getValue());
        if (isActivated && verifiedPass) {
            isOpened = true;
        }
    }

    @Override
    public void open() {
        System.out.println("WARNING: Password required!");
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getName() {
        return String.format("Safe for %.2f", price);
    }

    public Password getPassword() {
        return password;
    }

    public String getBriefName() {
        return briefName;
    }

    @Override
    public String toString() {
        return String.format("Safe:%n\tActive: %s%n\tOpen: %s",
                isActivated, isOpened);
    }
}
