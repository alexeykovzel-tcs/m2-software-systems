package ss.week3.hotel;

import ss.week3.hotel.Room;

/**
 * @author Aliaksei and Vlad
 */
public class Guest {
    private final String name;
    private Room room;

    //@ private invariant name != null;

    /**
     * Just a basic constructor
     * @param name guest name
     */
    public Guest(String name) {
        this.name = name;
    }

    /**
     * To get a guest name
     * @return guest name
     */
    /*@
        requires name != null && name != "";
        ensures \result != null && \result != "";
     */
    public String getName() {
        return name;
    }

    /**
     * To get a guest room
     * @return guest room
     */
    /*@
        requires room != null;
        ensures \result != null;
     */
    public Room getRoom() {
        return room;
    }

    /**
     * To assign a room number to a guest
     * @param room a room
     * @return whether a guest has signed in or not
     */
    /*@
        requires room != null;
     */
    public boolean checkin(Room room) {
        if (room.getGuest() == null){
            room.setGuest(this);
            this.room = room;
            return true;
        }
        return false;
    }

    /**
     * To free a room from the guest
     * @return whether a guest has left or not
     */
    public boolean checkout() {
        if (room != null){
            room.setGuest(null);
            room = null;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Guest %s is in the room number %d", name, room.getNumber());
    }
}
