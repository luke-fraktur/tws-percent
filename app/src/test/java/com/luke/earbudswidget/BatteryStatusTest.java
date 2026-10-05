package com.luke.earbudswidget;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BatteryStatusTest {
    @Test
    public void unknownLevelIsClassifiedAsUnknown() {
        assertEquals(BatteryStatus.Level.UNKNOWN, BatteryStatus.classify(-1));
    }

    @Test
    public void lowRangeIsClassifiedCorrectly() {
        assertEquals(BatteryStatus.Level.LOW, BatteryStatus.classify(0));
        assertEquals(BatteryStatus.Level.LOW, BatteryStatus.classify(29));
    }

    @Test
    public void mediumRangeIncludesBoundaries() {
        assertEquals(BatteryStatus.Level.MEDIUM, BatteryStatus.classify(30));
        assertEquals(BatteryStatus.Level.MEDIUM, BatteryStatus.classify(69));
    }

    @Test
    public void highRangeIncludesBoundaryAndMaximum() {
        assertEquals(BatteryStatus.Level.HIGH, BatteryStatus.classify(70));
        assertEquals(BatteryStatus.Level.HIGH, BatteryStatus.classify(100));
    }
}
