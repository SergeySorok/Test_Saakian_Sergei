package org.example.ticket;

import lombok.Getter;
import lombok.Setter;
import org.example.service.TimeUnits;
import org.example.service.TimeZone;

import java.text.ParseException;
import java.text.SimpleDateFormat;


@Setter
@Getter
public class Ticket {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("HH:mm dd.MM.yy XXX");


    private String origin;
    private String origin_name;
    private String destination;
    private String destination_name;
    private String departure_date;
    private String departure_time;
    private String arrival_date;
    private String arrival_time;
    private String carrier;
    private int stops;
    private int price;

    public Ticket() {
    }


    public int getFlightTime() {
        long departureTimeInMS = 0;
        long arrivalTimeInMS = 0;
        try {
            departureTimeInMS = SIMPLE_DATE_FORMAT
                    .parse(this.departure_time + " " + this.departure_date + " " + TimeZone.VLADIVOSTOK.getTimeUnits()).getTime();
            arrivalTimeInMS = SIMPLE_DATE_FORMAT
                    .parse(this.arrival_time + " " + this.arrival_date + " " + TimeZone.TEL_AVIV.getTimeUnits()).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
            long flightTimePerMinutes = (arrivalTimeInMS - departureTimeInMS)
                    / TimeUnits.MILLISECOND_PER_SECOND.getTimeUnits()
                    / TimeUnits.SECONDS_PER_MINUTE.getTimeUnits();
            return (int) flightTimePerMinutes;
        }
}
