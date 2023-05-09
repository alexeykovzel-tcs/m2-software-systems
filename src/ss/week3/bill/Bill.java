package ss.week3.bill;

import ss.week3.bill.printer.Printer;

import java.util.ArrayList;
import java.util.List;

public class Bill {
    private final Printer printer;
    private final List<Item> items;
    private boolean isClosed;

    /**
     * Constructs a Bill sending the output to a given Printer.
     *
     * @param printer printer
     */
    public Bill(Printer printer) {
        this.printer = printer;
        items = new ArrayList<>();
    }

    public interface Item {
        /**
         * Returns the price of this Item.
         *
         * @return item price
         */
        double getPrice();

        /**
         * Return the name of this item.
         *
         * @return item name
         */
        String getName();
    }

    /**
     * Adds an item to this Bill.
     *
     * @param item the new item
     */
    public void addItem(Item item) {
        if (!isClosed) {
            items.add(item);
            printer.printLine(item.getName(), item.getPrice());
        }
    }

    /**
     * Sends the sum total of the bill to the printer.
     */
    public void close() {
        if (!isClosed) {
            isClosed = true;
            printer.printLine("Total", getSum());
        }
    }

    /**
     * Returns the current sum total of the Bill.
     *
     * @return the current sum total of the Bill
     */
    public double getSum() {
        return items.stream().mapToDouble(Item::getPrice).sum();
    }

    /**
     * To retrieve bill items .
     *
     * @return bill items
     */
    public List<Item> getItems() {
        return items;
    }
}
