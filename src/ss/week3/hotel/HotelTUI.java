package ss.week3.hotel;

import ss.utils.TextIO;
import ss.week3.bill.Bill;
import ss.week3.bill.printer.StringPrinter;
import ss.week3.bill.printer.SysoutPrinter;

import java.util.stream.IntStream;

/**
 * A simple interactive Hotel TUI
 *
 * @author Aliaksei & Vlad
 */
public class HotelTUI {
    // User commands (constants)
    private static final String CHECK_IN = "i";
    private static final String CHECK_OUT = "o";
    private static final String REQUEST_ROOM = "r";
    private static final String ACTIVATE_SAFE = "a";
    private static final String GET_BILL = "b";
    private static final String HELP = "h";
    private static final String PRINT_HOTEL_STATE = "p";
    private static final String EXIT = "x";

    private final Hotel hotel;

    public HotelTUI(String hotelName) {
        Hotel hotel = new Hotel(hotelName);
        IntStream.range(1, 3).forEach(i ->
                hotel.addRoom(new PricedRoom(i + 100, Hotel.ROOM_PRICE, Hotel.SAFE_PRICE)));
        this.hotel = hotel;
    }

    // NOTE: because classes/objects have not been introduced in week 1,
    // everything here is static so it can be used from static methods.
    // Obviously this is not how you should program once you know how
    // object oriented programming works.


    /**
     * Implementation of the "check in" command
     */
    void doCheckIn(String[] splittedCommand) {
        if (splittedCommand.length == 2) {
            String guestName = splittedCommand[1];
            Room room = hotel.checkIn(guestName);

            if (room != null) {
                System.out.printf("Guest %s is checked into room %d%n%n", guestName, room.getNumber());
            } else {
                System.out.printf("ERROR: There is no free room or this guest has already checked in%n%n");
            }
        } else {
            System.out.println("ERROR: Wrong parameters at check in\n");
        }
    }

    /**
     * Implementation of the "check out" command
     */
    void doCheckOut(String[] splittedCommand) {
        if (splittedCommand.length == 2) {
            String guestName = splittedCommand[1];
            // Check if we can actually check out this guest
            Room room = hotel.getRoom(guestName);

            if (room != null) {
                hotel.checkOut(guestName);
                System.out.printf("Guest %s successfully checked out%n%n", guestName);
            } else {
                System.out.printf("ERROR: There is no guest with name %s%n%n", guestName);
            }
        } else {
            System.out.println("ERROR: Wrong parameters at check out\n");
        }
    }

    /**
     * Implementation of the "get room of guest" command
     */
    void doRequestRoom(String[] splittedCommand) {
        if (splittedCommand.length == 2) {
            String guestName = splittedCommand[1];
            // Since we only model a single room hotel, this is pretty easy to do
            Room room = hotel.getRoom(guestName);

            if (room != null) {
                System.out.printf("Guest %s has room %d%n%n", guestName, room.getNumber());
            } else {
                System.out.printf("ERROR: Guest %s doesn't have a room%n%n", guestName);
            }
        } else {
            System.out.println("ERROR: Wrong parameters at get room request\n");
        }
    }

    void doActivateSafe(String[] splittedCommand) {
        if (splittedCommand.length == 3) {
            String guestName = splittedCommand[1];
            Room room = hotel.getRoom(guestName);

            if (room != null) {
                Safe safe = room.getSafe();

                if (!safe.isActive()) {
                    String password = splittedCommand[2];
                    ((PricedSafe) safe).activate(password);

                    if (safe.isActive()) {
                        System.out.printf("Safe in room %d of guest %s has been activated%n%n",
                                room.getNumber(), guestName);
                    } else {
                        System.out.println("ERROR: Failed to activate safe\n");
                    }
                } else {
                    System.out.printf("ERROR: Safe of room %d is already activated%n%n", room.getNumber());
                }
            } else {
                System.out.printf("ERROR: Guest %s doesn't have a room%n%n", guestName);
            }
        } else {
            System.out.println("ERROR: Wrong parameters at activation\n");
        }
    }

    void doGetBill(String[] splittedCommand) {
        if (splittedCommand.length == 3) {
            String guestName = splittedCommand[1];
            Room room = hotel.getRoom(guestName);

            if (room != null) {
                if (room instanceof PricedRoom) {
                    try {
                        int nights = Integer.parseInt(splittedCommand[2]);
                        StringPrinter printer = new StringPrinter();
                        hotel.getBill(guestName, nights, printer);
                        System.out.println(printer.getResult());
                    } catch (NumberFormatException e) {
                        System.out.println("ERROR: Invalid number of nights\n");
                    }
                }
            } else {
                System.out.printf("ERROR: Guest %s doesn't have a room%n%n", guestName);
            }
        } else {
            System.out.println("ERROR: Wrong params at activation\n");
        }
    }

    /**
     * Implementation of the "print state" command.
     * <p>
     * For every room in the hotel, print who occupies it if anyone
     */
    void doPrintHotelState(String[] splittedCommand) {
        if (splittedCommand.length == 1) {
            System.out.println(hotel);
        } else {
            System.out.println("ERROR: Wrong params at activation\n");
        }
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
                case CHECK_IN:
                    doCheckIn(splittedCommand);
                    break;
                case CHECK_OUT:
                    doCheckOut(splittedCommand);
                    break;
                case REQUEST_ROOM:
                    doRequestRoom(splittedCommand);
                    break;
                case PRINT_HOTEL_STATE:
                    doPrintHotelState(splittedCommand);
                    break;
                case ACTIVATE_SAFE:
                    doActivateSafe(splittedCommand);
                    break;
                case GET_BILL:
                    doGetBill(splittedCommand);
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
                    System.out.printf("ERROR: Unknown command: %s.", command);
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
                        "- %s name password... activate safe, password required for PricedSafe%n" +
                        "- %s name nights..... print bill for guest (name) and number of nights%n" +
                        "- %s ................ help (this menu)%n" +
                        "- %s ................ print state%n" +
                        "- %s ................ exit%n%n",
                CHECK_IN, CHECK_OUT, REQUEST_ROOM, ACTIVATE_SAFE, GET_BILL, HELP, PRINT_HOTEL_STATE, EXIT);
    }

    public static void main(String[] args) {
        new HotelTUI("U Parkhotel").start();
    }
}
