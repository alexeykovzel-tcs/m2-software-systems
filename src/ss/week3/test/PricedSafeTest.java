package ss.week3.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ss.week3.hotel.PricedSafe;

import static org.junit.jupiter.api.Assertions.*;

public class PricedSafeTest {
    private PricedSafe safe;
    private static final double PRICE = 6.36;
    private static final String PRICE_PATTERN = ".*6[.,]36.*";

    public String CORRECT_PASSWORD;
    public String INCORRECT_PASSWORD;

    @BeforeEach
    void setUp() {
        safe = new PricedSafe(PRICE);
        CORRECT_PASSWORD = safe.getPassword().getValue();
        INCORRECT_PASSWORD = CORRECT_PASSWORD + "WRONG";
        assertFalse(safe.isActive());
        assertFalse(safe.isOpen());
    }

    @Test
    void testIsBillItem() {
        assertNotNull(safe, "safe should be an instance of Bill.Item.");
        assertEquals(PRICE, safe.getPrice(), 0,
                "GetPrice should return the price of the safe.");
    }

    @Test
    void testGetPrice() {
        assertEquals(safe.getPrice(), PRICE);
    }

    @Test
    void testToString() {
        String stringValue = safe.toString();
        assertTrue(stringValue.contains("Safe"));
        assertTrue(stringValue.contains("Active: " + safe.isActive()));
        assertTrue(stringValue.contains("Open: " + safe.isOpen()));
    }

    @Test
    void testToActivateWithCorrectPassword() {
        assertFalse(safe.isActive());
        assertFalse(safe.isOpen());
        safe.activate(CORRECT_PASSWORD);
        assertTrue(safe.isActive());
        assertFalse(safe.isOpen());
    }

    @Test
    void testToActivateWithIncorrectPassword() {
        assertFalse(safe.isActive());
        assertFalse(safe.isOpen());
        safe.activate(INCORRECT_PASSWORD);
        assertFalse(safe.isActive());
        assertFalse(safe.isOpen());
    }

    @Test
    void testToOpenWithCorrectPassword() {
        safe.activate(CORRECT_PASSWORD);
        assertTrue(safe.isActive());
        assertFalse(safe.isOpen());
        safe.open(CORRECT_PASSWORD);
        assertTrue(safe.isActive());
        assertTrue(safe.isOpen());
    }

    @Test
    void testToFailOpenWithIncorrectPassword() {
        safe.activate(CORRECT_PASSWORD);
        assertTrue(safe.isActive());
        assertFalse(safe.isOpen());
        safe.open(INCORRECT_PASSWORD);
        assertTrue(safe.isActive());
        assertFalse(safe.isOpen());
    }

    @Test
    void testToCloseWhenActivated() {
        safe.activate(CORRECT_PASSWORD);
        safe.open(CORRECT_PASSWORD);
        assertTrue(safe.isActive());
        assertTrue(safe.isOpen());
        safe.close();
        assertTrue(safe.isActive());
        assertFalse(safe.isOpen());
    }

    @Test
    void testToCloseWhenDeactivated() {
        assertFalse(safe.isActive());
        assertFalse(safe.isOpen());
        safe.close();
        assertFalse(safe.isActive());
        assertFalse(safe.isOpen());
    }
}
