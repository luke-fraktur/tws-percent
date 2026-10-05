package com.luke.earbudswidget;

/** Pure battery-level classification used by the widget and unit tests. */
public final class BatteryStatus {
    public enum Level {
        UNKNOWN,
        LOW,
        MEDIUM,
        HIGH
    }

    private BatteryStatus() {
    }

    public static Level classify(int level) {
        if (level < 0) return Level.UNKNOWN;
        if (level >= 70) return Level.HIGH;
        if (level >= 30) return Level.MEDIUM;
        return Level.LOW;
    }
}
