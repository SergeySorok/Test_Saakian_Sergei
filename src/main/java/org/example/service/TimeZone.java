package org.example.service;

public enum TimeZone {
    VLADIVOSTOK("+10:00"),
    TEL_AVIV("+03:00");

    private final String timeZOne;

    TimeZone(String timeZone) {
        this.timeZOne = timeZone;
    }

    public String getTimeUnits() {
        return timeZOne;
    }

}
