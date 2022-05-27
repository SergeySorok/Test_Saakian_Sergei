package org.example.service;

import lombok.Getter;
import lombok.Setter;
import org.example.ticket.Tickets;

@Setter
@Getter

public class TicketAnalyticsService {
    Tickets tickets;

    public TicketAnalyticsService(Tickets tickets) {
        this.tickets = tickets;
    }

    public String percentileResult() {
        int percentile = tickets.getPercentileInMinutes();
        return "The flight time percentile is " + resultToString(percentile);
    }


    public String averageFlightTime() {
        int allTicketsTimeFlight = tickets.getAverageFlightTimeInMinutes();
        return "The average flight time is " + resultToString(allTicketsTimeFlight);
    }

    public String averageFlightTime(String departurePoint, String placeOfArrival) {
        int allTicketsTimeFlight = tickets.getAverageFlightTimeInMinutes(departurePoint, placeOfArrival);
        return "The average flight time between " + departurePoint + " and "
                + placeOfArrival + " is " + resultToString(allTicketsTimeFlight);
    }

    private String resultToString(int minutes) {
        return minutes / TimeUnits.MINUTES_PER_HOUR.getTimeUnits() + " hours "
                + minutes % TimeUnits.MINUTES_PER_HOUR.getTimeUnits() + " min.";
    }
}
