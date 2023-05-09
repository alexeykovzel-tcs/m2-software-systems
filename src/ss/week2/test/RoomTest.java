package ss.week2.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ss.week2.hotel.Guest;
import ss.week2.hotel.Room;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomTest {
    private Guest guest;
    private Room room;

    @BeforeEach
    public void setUp() {
        guest = new Guest("Jip");
        room = new Room(101);
    }

    @Test
    public void testSetUp() {
        assertEquals(101, room.getNumber());
    }

    @Test
    public void ifContainsSafe() {
        assert (room.getSafe() != null);
    }

    @Test
    public void testSetGuest() {
        room.setGuest(guest);
        assertEquals(guest, room.getGuest());
    }
}
