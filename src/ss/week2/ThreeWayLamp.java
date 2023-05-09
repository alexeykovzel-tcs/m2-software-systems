package ss.week2;

import ss.week2.hotel.Safe;

import java.util.Arrays;

// write JML: invariants, preconditions and postconditions for all methods.
public class ThreeWayLamp {

    public static final LampSetting[] SETTINGS_IN_ORDER =
            {LampSetting.OFF, LampSetting.LOW, LampSetting.MEDIUM, LampSetting.HIGH};

    public enum LampSetting {
        OFF, LOW, MEDIUM, HIGH, STATE, NEXT, HELP, EXIT;

        public static boolean isValid(String settingName) {
            return Arrays.stream(values()).anyMatch(val -> val.name().equals(settingName));
        }

        public static LampSetting getNext(LampSetting setting) {
            for (LampSetting s : SETTINGS_IN_ORDER) {
                if (s.equals(setting)) {
                    return SETTINGS_IN_ORDER[((setting.ordinal() + 1) % SETTINGS_IN_ORDER.length)];
                }
            }
            return null;
        }
    }

    private LampSetting setting = LampSetting.OFF;

    //@ private invariant setting != null;

    /*@
        requires setting != null;
        ensures setting != null;
    */
    public void switchSetting() {
        setting = LampSetting.getNext(setting);
    }

    /*@
        ensures setting != null;
    */
    public void setSetting(LampSetting setting) {
        this.setting = setting;
    }

    /*@
        requires setting != null;
        ensures \result != null;
    */
    public LampSetting getSetting() {
        return setting;
    }
}
