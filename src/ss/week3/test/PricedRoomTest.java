package ss.week3.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ss.week3.bill.Bill;
import ss.week3.hotel.PricedRoom;

public class PricedRoomTest {
    private Bill.Item item;
    private static final double PRICE = 6.36;
    private static final String PRICE_PATTERN = ".*6[.,]36.*";

    @BeforeEach
    public void setUp() throws Exception {
        item = new PricedRoom(0, PRICE, 0);
    }

    @Test
    public void testGetPrice() {
        assertEquals(PRICE, item.getPrice(), 0,
        		"GetPrice should return the price of the room.");
    }

    @Test
    public void testToString() {
        // We had to replace the "\n" notation as otherwise it doesn't find patterns
        String stringValue = item.toString().replace("\n", "");
        assertTrue(stringValue.matches(PRICE_PATTERN),
        		"The price per night should be included.");
    }
}
