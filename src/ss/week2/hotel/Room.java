package ss.week2.hotel;

import java.util.Objects;

public class Room {
    private final int number;
    private Guest guest;
    private Safe safe;

    /**
     * Creates a <code>Room</code> with the given number, without a guest.
     *
     * @param number number of the new <code>Room</code>
     */
    public Room(int number) {
        this(number, new Safe());
    }

    public Room(int number, Safe safe) {
        this.number = number;
        this.safe = safe;
    }

    /**
     * Returns the number of this Room
     */
    public int getNumber() {
        return number;
    }

    /**
     * Returns the current guest living in this Room
     *
     * @return the guest of this Room, null if not rented
     */
    public Guest getGuest() {
        return guest;
    }


    /**
     * Assigns a Guest to this Room.
     *
     * @param guest the new guest renting this Room, if null is given, Room is empty afterwards
     */
    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Safe getSafe() {
        return safe;
    }

    @Override
    public String toString() {
        return String.format("Room with number %d has a guest %s", number, guest.getName());
    }
}
