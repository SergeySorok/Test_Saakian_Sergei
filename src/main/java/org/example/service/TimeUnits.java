package org.example.service;

public enum TimeUnits {
    MILLISECOND_PER_SECOND(1000),
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
