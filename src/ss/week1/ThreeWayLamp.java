package ss.week1;

import ss.utils.TextIO;

public class ThreeWayLamp {
    public enum LampSetting {
        OFF, LOW, MEDIUM, HIGH, STATE, NEXT, HELP, EXIT;

        public static boolean isValid(String settingValue) {
            for (LampSetting lampSetting : values()) {
                if (lampSetting.name().equals(settingValue)) {
                    return true;
                }
            }
            return false;
        }
    }

    static LampSetting currSetting = LampSetting.OFF;
    static boolean isOn = true;

    public static void main(java.lang.String[] args) throws Exception {
        System.out.println(getHelpMenu());

        while (isOn) {
            System.out.println("\n--- Please input the next option: ---\n");
            String optionValue = TextIO.getln();

            if (LampSetting.isValid(optionValue)) {
                setLampSetting(LampSetting.valueOf(optionValue));
            } else {
                System.out.println("This option is invalid.");
            }
        }
    }

    public static void switchSetting() throws Exception {
        LampSetting[] optionsInOrder = {LampSetting.OFF, LampSetting.LOW, LampSetting.MEDIUM, LampSetting.HIGH};
        int settingCount = optionsInOrder.length;
        boolean settingFound = false;

        for (int i = 0; i < settingCount; i++) {
            if (optionsInOrder[i].equals(getSetting())) {
                int nextIdx = (i == settingCount - 1) ? 0 : i + 1;
                currSetting = optionsInOrder[nextIdx];
                settingFound = true;
                break;
            }
        }

        if (!settingFound) {
            throw new Exception("Invalid current setting");
        }
    }

    public static LampSetting getSetting() {
        return currSetting;
    }

    public static void setLampSetting(LampSetting setting) throws Exception {
        switch (setting) {
            case OFF:
            case LOW:
            case MEDIUM:
            case HIGH:
                System.out.printf("State is set to %s%n", setting);
                currSetting = setting;
                break;
            case STATE:
                System.out.println(getSetting());
                break;
            case NEXT:
                switchSetting();
                System.out.printf("State is now set to %s%n", getSetting());
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