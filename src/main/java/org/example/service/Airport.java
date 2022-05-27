package org.example.service;

import lombok.Getter;

import java.time.ZoneOffset;

@Getter
public enum Airport {
    VVO(10),
    TLV(3);

    private final ZoneOffset offset;

    Airport(int offsetHours) {
        this.offset = ZoneOffset.ofHours(offsetHours);
    }

    public static Airport byName(String name) {
        if (name == null) {
            return null;
        }

        for (Airport airport : values()) {
            if (airport.name().equals(name)) {
                return airport;
            }
        }
        return null;
    }
}
