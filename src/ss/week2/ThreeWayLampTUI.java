package ss.week2;

import ss.utils.TextIO;

public class ThreeWayLampTUI {
    private static ss.week2.ThreeWayLamp threeWayLamp;
    static boolean isOn = true;

    public static void main(java.lang.String[] args) throws Exception {
        threeWayLamp = new ss.week2.ThreeWayLamp();
        System.out.println(getHelpMenu());

        while (isOn) {
            System.out.println("\n--- Please input the next option: ---\n");
            String optionValue = TextIO.getln();

            if (ThreeWayLamp.LampSetting.isValid(optionValue)) {
                ThreeWayLamp.LampSetting nextSetting = ThreeWayLamp.LampSetting.valueOf(optionValue);
                handleSetting(nextSetting);
            } else {
                System.out.println("This option is invalid.");
            }
        }
    }

    public static void handleSetting(ThreeWayLamp.LampSetting nextSetting) {
        switch (nextSetting) {
            case OFF:
            case LOW:
            case MEDIUM:
            case HIGH:
                threeWayLamp.setSetting(nextSetting);
                System.out.printf("State is now set to %s%n", threeWayLamp.getSetting());
                break;
            case STATE:
                System.out.println(threeWayLamp.getSetting());
                break;
            case NEXT:
                threeWayLamp.switchSetting();
                System.out.printf("State is now set to %s%n", threeWayLamp.getSetting());
                break;
            case HELP:
                System.out.println(getHelpMenu());
                break;
            case EXIT:
                isOn = false;
        }
    }

    private static String getHelpMenu() {
        return "--- I'm a just a lamp. You can control me by using these options: ---\n\n" +
                "OFF: Set the lamp to OFF (default value)\n" +
                "LOW: Set the lamp to LOW\n" +
                "MEDIUM: Set the lamp to MEDIUM\n" +
                "HIGH: Set the lamp to HIGH\n" +
                "STATE: Print the current setting of the lamp\n" +
                "NEXT: Change to the next setting, observing the order OFF → LOW → MEDIUM → HIGH → OFF\n" +
                "HELP: Show a help menu, explaining how the user should interact with the program\n" +
                "EXIT: Quit the program";
    }
}
