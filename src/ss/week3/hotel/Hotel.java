package ss.week3.hotel;

import ss.week3.bill.Bill;
import ss.week3.bill.printer.StringPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Hotel {
    public static final double ROOM_PRICE = 10;
    public static final double SAFE_PRICE = 20;
    private final String name;
    private final List<Room> rooms;

    /*@
        private invariant name != null;
     */

    public Hotel(String name) {
        rooms = new ArrayList<>();
        this.name = name;
    }

    /*@
        requires guestName != null && guestName != "";
        requires rooms.size() > 0;
     */
    public Room checkIn(String guestName) {
        boolean alreadyCheckedIn = rooms.stream()
                .anyMatch(room -> {
                    if (room.getGuest() != null) {
                        return room.getGuest().getName().equals(guestName);
                    }
                    return false;
                });

        if (!alreadyCheckedIn) {
            Room freeRoom = getFreeRoom();
            if (freeRoom != null) {
                freeRoom.setGuest(new Guest(guestName));
                return freeRoom;
            }
        }
        return null;
    }

    /*@
        requires guestName != null && guestName != "";
        requires rooms.size() > 0;
     */
    public void checkOut(String guestName) {
        for (Room room : rooms) {
            Guest guest = room.getGuest();
            if (guest != null && guest.getName().equals(guestName)) {
                room.getSafe().deactivate();
                room.setGuest(null);
            }
        }
    }

    public Bill getBill(String guestName, int nights, StringPrinter printer) {
        PricedRoom room = (PricedRoom) getRoom(guestName);
        if (room != null) {
            PricedSafe safe = (PricedSafe) room.safe;
            Bill bill = new Bill(printer);

            IntStream.range(0, nights).forEach(i -> bill.addItem(room));
            if (safe.isActivated) {
                bill.addItem(safe);
            }

            bill.close();
            return bill;
        }
        return null;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    /*@
        requires rooms.size() > 0;
     */
    public Room getFreeRoom() {
        for (Room room : rooms) {
            if (room.getGuest() == null) {
                return room;
            }
        }
        return null;
    }

    /*@
        requires guestName != null && guestName != "";
        requires rooms.size() > 0;
     */
    public Room getRoom(String guestName) {
        for (Room room : rooms) {
            Guest guest = room.getGuest();
            if (guest != null && guest.getName().equals(guestName)) {
                return room;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    /*@
        ensures \result != null && \result != "";
     */
    @Override
    public String toString() {
        StringBuilder hotelDescription = new StringBuilder();
        hotelDescription.append(String.format("Hotel %s:\n", name));
        rooms.forEach(room -> hotelDescription.append(room.toString()));
        return hotelDescription.toString();
    }
}
