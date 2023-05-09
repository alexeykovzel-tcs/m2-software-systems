package ss.week2.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ss.week2.ThreeWayLamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ss.week2.ThreeWayLamp.SETTINGS_IN_ORDER;

public class ThreeWayLampTest {
    private ThreeWayLamp threeWayLamp;

    @BeforeEach
    void setUp(){
        threeWayLamp = new ThreeWayLamp();
    }

    @Test
    void isOffAfterInit(){
        assertEquals(threeWayLamp.getSetting(), ThreeWayLamp.LampSetting.OFF);
    }

    @Test
    void isEachSettingInOrder() {
        for (ThreeWayLamp.LampSetting setting : SETTINGS_IN_ORDER){
            if (setting.equals(threeWayLamp.getSetting())){
                continue;
            }
            threeWayLamp.switchSetting();
            assertEquals(threeWayLamp.getSetting(), setting);
        }
    }
}
