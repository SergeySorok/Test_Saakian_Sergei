package org.example.service;

public enum TimeUnits {
    SECONDS_PER_MINUTE(60),
    MINUTES_PER_HOUR(60);

    private final int timeUnits;

    TimeUnits(int timeUnit) {
        this.timeUnits = timeUnit;
    }

    public int getTimeUnits() {
        return timeUnits;
    }
}
