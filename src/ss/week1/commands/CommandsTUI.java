package ss.week1.commands;

import ss.utils.TextIO;
import ss.week1.BabylonianAlgorithm;
import ss.week1.Fibonacci;

import java.util.Arrays;

public class CommandsTUI {
    private static boolean isOn = true;

    enum Command {
        FIB, BABY, MAX, HELP, EXIT;

        public static boolean isValid(String commandName) {
            for (Command command : values()) {
                if (command.name().equals(commandName)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Greetings Human !\n" + getHelpMenu());

        while (isOn) {
            // get user input from a command line
            System.out.print("\nCommand : ");
            String lnInput = TextIO.getln();

            // convert an input value into a command and its arguments
            String[] inputs = lnInput.split(" ");
            String commandValue = inputs[0];

            int inputsCount = inputs.length;
            String[] commandArgs = inputsCount > 1 ? Arrays.copyOfRange(inputs, 1, inputsCount) : null;

            // handle a command if it's valid
            if (Command.isValid(commandValue)) {
                handleCommand(Command.valueOf(commandValue), commandArgs);
            } else {
                System.out.printf("Command '%s' is invalid%n", lnInput);
            }
        }
    }

    private static void handleCommand(Command command, String[] args) {
        switch (command) {
            case FIB:
                if (args != null && args.length == 1) {
                    try {
                        int fibIndex = Integer.parseInt(args[0]);
                        long fibNumber = Fibonacci.fibonacciRecursive(fibIndex);
                        System.out.printf("Fibonacci number with index %s is : %s%n", fibIndex, fibNumber);
                    } catch (Exception e) {
                        System.out.println("ERROR: Failed to get a fibonacci number.");
                    }
                } else {
                    System.out.println("ERROR: Invalid arguments.");
                }
                break;
            case BABY:
                try {
                    if (args != null && args.length == 1) {
                        double input = Double.parseDouble(args[0]);
                        double result = BabylonianAlgorithm.babylonAlgorithm(input);
                        System.out.printf("Babylonian number with index input %.2f is : %.2f%n", input, result);
                    } else {
                        System.out.println("ERROR: Invalid arguments.");
                    }
                } catch (Exception e) {
                    System.out.println("ERROR: Failed to get a babylonian number.");
                }
                break;
            case MAX:
                try {
                    int maxNumber = findMaximum(Arrays.stream(args).map(Integer::parseInt).toArray(Integer[]::new));
                    System.out.printf("The maximum number is : %s%n", maxNumber);
                } catch (Exception e) {
                    System.out.println("ERROR: Failed to get the maximum number.");
                }
                break;
            case HELP:
                System.out.println(getHelpMenu());
                break;
            case EXIT:
                isOn = false;
                break;
            default:
                System.out.println("ERROR: Invalid command value.");
        }
    }

    private static int findMaximum(Integer[] numbers) {
        int maxNumber = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            int nextNumber = numbers[i];
            if (maxNumber < nextNumber) {
                maxNumber = nextNumber;
            }
        }
        return maxNumber;
    }

    private static String getHelpMenu() {
        return "\nThrough this application you have access to the following commands :\n" +
                "1. Fibonacci number : FIB integer\n" +
                "2. Babylonian Algorithm : BABY double\n" +
                "3. Maximum number : MAX list_of_integers\n" +
                "4. Help menu : HELP\n" +
                "5. Exit application : EXIT";
    }
}
