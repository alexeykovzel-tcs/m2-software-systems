package ss.week3.hotel;

import ss.week3.bill.Bill;

public class PricedRoom extends Room implements Bill.Item {
    private final double price;

    public PricedRoom(int number, double roomPrice, double safePrice) {
        super(number, new PricedSafe(safePrice));
        price = roomPrice;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getName() {
        return String.format("Room %d (%.2f/night)", number, price);
    }

    @Override
    public String toString() {
        return String.format("%s:\n\t\trented by: %s\n\t\tsafe active: %s%n",
                getName(), (guest != null ? guest.getName() : "null"), getSafe().isActive());
    }
}
