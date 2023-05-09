package ss.week3.test;

import org.junit.jupiter.api.*;
import ss.week3.bill.Bill;
import ss.week3.bill.printer.Printer;
import ss.week3.bill.printer.StringPrinter;

import static org.junit.jupiter.api.Assertions.*;

public class BillTest {
    private Bill bill;
    private StringPrinter printer;

    @BeforeEach
    void setUp() {
        printer = new StringPrinter();
        bill = new Bill(printer);
    }

    @Test
    void testBeginState() {
        assertEquals(bill.getItems().size(), 0);
    }

    @Test
    void testNewItem() {
        bill.addItem(new Item("Item 1", 2));
        bill.addItem(new Item("Item 2", 3));

        assertEquals(bill.getItems().size(), 2);
        assertEquals(bill.getSum(), 5);

        bill.close();
        String result = printer.getResult();

        assertTrue(result.contains("Item 1"));
        assertTrue(result.contains("2.00"));
        assertTrue(result.contains("Item 2"));
        assertTrue(result.contains("3.00"));
        assertTrue(result.contains("Total"));
        assertTrue(result.contains("5.00"));
    }

    private static class Item implements Bill.Item {
        private final String text;
        private final double price;

        Item(String text, double price) {
            this.text = text;
            this.price = price;
        }

        @Override
        public double getPrice() {
            return price;
        }

        @Override
        public String getName() {
            return text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

}

