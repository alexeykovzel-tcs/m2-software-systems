package ss.week2.hotel;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private final String name;
    private final List<Room> rooms;

    /*@
        private invariant name != null;
     */

    public Hotel(String name) {
        rooms = new ArrayList<>();
        this.name = name;
    }

    public void addRoom(Room room){
        rooms.add(room);
    }

    /*@
        requires guestName != null && guestName != "";
        requires rooms.size() > 0;
     */
    public Room checkIn(String guestName) {
        boolean alreadyCheckedIn = rooms.stream()
                .anyMatch(room -> room.getGuest() != null && room.getGuest().getName().equals(guestName));

        if (!alreadyCheckedIn) {
            Room freeRoom = getFreeRoom();
            if (freeRoom != null){
                freeRoom.getSafe().activate();
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

        for (Room room : rooms) {
            if (room.getGuest() != null) {
                hotelDescription.append(String.format("Room %d is occupied by %s ",
                        room.getNumber(),
                        room.getGuest().getName()));
            } else {
                hotelDescription.append(String.format("Room %d is currently empty ",
                        room.getNumber()));
            }
            hotelDescription.append(String.format("with safe %s and %s\n",
                    room.getSafe().isActive() ? "activated" : "deactivated",
                    room.getSafe().isOpen() ? "opened" : "closed"));
        }
        return hotelDescription.toString();
    }
}
