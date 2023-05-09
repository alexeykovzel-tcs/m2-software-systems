package ss.week2.hotel;

import ss.utils.TextIO;

import java.util.stream.IntStream;

/**
 * A simple interactive Hotel TUI
 *
 * @author Aliaksei & Vlad
 */

public class HotelTUI {
    // User commands (constants)
    static final String IN = "i";
    static final String OUT = "o";
    static final String ROOM = "r";
    static final String PRINT = "p";
    static final String HELP = "h";
    static final String EXIT = "x";

    private final Hotel hotel;

    public HotelTUI(String hotelName) {
        Hotel hotel = new Hotel(hotelName);
        IntStream.range(1, 3).forEach(i ->
                hotel.addRoom(new Room(i + 100)));
        this.hotel = hotel;
    }

    // NOTE: because classes/objects have not been introduced in week 1,
    // everything here is static so it can be used from static methods.
    // Obviously this is not how you should program once you know how
    // object oriented programming works.


    /**
     * Implementation of the "check in" command
     */
    private void doIn(String[] splittedCommand) {
        if (splittedCommand.length == 2) {
            String guestName = splittedCommand[1];
            Room room = hotel.checkIn(guestName);

            if (room != null) {
                System.out.printf("Guest %s gets room %s%n", guestName, room.getNumber());
            } else {
                System.out.printf("Checkin failed: room is taken or this guest has already checked in.%n");
            }
        } else {
            System.out.println("Wrong parameters at check in");
        }
    }

    /**
     * Implementation of the "check out" command
     */
    private void doOut(String[] splittedCommand) {
        if (splittedCommand.length == 2) {
            String guestName = splittedCommand[1];
            // Check if we can actually check out this guest

            if (hotel.getRoom(guestName) != null) {
                hotel.checkOut(guestName);
                System.out.printf("Guest %s successfully checked out.%n", guestName);
            } else {
                System.out.printf("Checkout failed: room is empty or is not taken by %s.%n", guestName);
            }
        } else {
            System.out.println("Wrong parameters at check out");
        }
    }

    /**
     * Implementation of the "get room of guest" command
     */
    private void doRoom(String[] splittedCommand) {
        String resultMessage;

        if (splittedCommand.length == 2) {
            String guestName = splittedCommand[1];
            Room room = hotel.getRoom(guestName);

            resultMessage = room != null
                    ? String.format("Guest %s has room %d.%n", guestName, room.getNumber())
                    : String.format("Guest %s doesn't have a room.%n", guestName);
        } else {
            resultMessage = "Wrong parameters at get room request";
        }

        System.out.println(resultMessage);
    }

    /**
     * Implementation of the "print state" command.
     * <p>
     * For every room in the hotel, print who occupies it if anyone
     */
    private void doPrint(String[] splittedCommand) {
        System.out.printf("Hotel %s:%n", hotel.getName());
        System.out.println(hotel);
    }

    public void start() {
        // Let's start with a friendly welcoming message and show the help menu
        System.out.println("Welcome to the Hotel booking system of " + hotel.getName());
        printHelpMenu();

        // Now until we are done, ask for user input
        boolean exit = false;
        while (!exit) {
            // First get a line of text from the user
            String input = TextIO.getlnString();

            // We want to extract the command (first word) so we split on (repeated) whitespace
            // the "\\s" means whitespace, the "+" means 1-or-more whitespace
            String[] splittedCommand = input.split("\\s+");

            // By the way, we know that input != null so split will go fine, and split always
            // returns an array of length at least 1, so we can access split[0] without checking bounds

            String command = splittedCommand[0];

            // To make our code not to complicated, we do all the actual commands
            // in separate methods, so it is easier to read here

            switch (command) {
                case IN:
                    doIn(splittedCommand);
                    break;
                case OUT:
                    doOut(splittedCommand);
                    break;
                case ROOM:
                    doRoom(splittedCommand);
                    break;
                case PRINT:
                    doPrint(splittedCommand);
                    break;
                case HELP:
                    // Just print the help menu...
                    printHelpMenu();
                    break;
                case EXIT:
                    // Set exit to true so we can leave the while loop
                    // If we just "return" here, we don't get the nice goodbye message!
                    exit = true;
                    break;
                default:
                    System.out.println("Unknown command: " + command);
                    printHelpMenu();
            }
        }

        System.out.println("Goodbye! We hope to see you again at " + hotel.getName());
    }

    public void printHelpMenu() {
        System.out.printf("Commands:%n\n" +
                        "- %s name ........... check in guest with name%n" +
                        "- %s name ........... check out guest with name%n" +
                        "- %s name ........... request room of guest%n" +
                        "- %s ................ help (this menu)%n" +
                        "- %s ................ print state%n" +
                        "- %s ................ exit%n%n",
                IN, OUT, ROOM, HELP, PRINT, EXIT);
    }

    public static void main(String[] args) {
        new HotelTUI("U Parkhotel").start();
    }
}
