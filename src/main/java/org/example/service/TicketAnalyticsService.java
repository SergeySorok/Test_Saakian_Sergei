package org.example.service;

import lombok.experimental.UtilityClass;
import org.example.ticket.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@UtilityClass
public class TicketAnalyticsService {

    public int getAverageFlightTimeInMinutes(List<Ticket> tickets, String departurePoint, String placeOfArrival) {
        int allFLightTimeInMinutes = 0;
        int requestTicketsSize = 0;

        for (Ticket ticket : tickets) {
            if (departurePoint.equals(ticket.getOrigin()) && placeOfArrival.equals(ticket.getDestination())) {
                allFLightTimeInMinutes += getFlightTime(ticket);
                requestTicketsSize++;
            }
        }
        return allFLightTimeInMinutes / requestTicketsSize;
    }

    public int getPercentileInMinutes(List<Ticket> tickets, int percentile, String origin, String destination) {
        List<Integer> flightTimeAllTickets = getAllFlightTime(tickets, origin, destination);
        Collections.sort(flightTimeAllTickets);

        int index = (int) Math.ceil(percentile / 100.0 * flightTimeAllTickets.size());
        return flightTimeAllTickets.get(index - 1);
    }

    private List<Integer> getAllFlightTime(List<Ticket> tickets, String origin, String destination) {
        List<Integer> allFlightTime = new ArrayList<>();
        for (Ticket ticket : tickets) {
            if (origin.equals(ticket.getOrigin()) && destination.equals(ticket.getDestination())) {
                allFlightTime.add(getFlightTime(ticket));
            }
        }
        return allFlightTime;
    }

    private int getFlightTime(Ticket ticket) {
        Airport departureAirport = Airport.byName(ticket.getOrigin());
        if (departureAirport == null) {
            throw new RuntimeException("Airport not found: " + ticket.getOrigin());
        }

        Airport arrivalAirport = Airport.byName(ticket.getDestination());
        if (arrivalAirport == null) {
            throw new RuntimeException("Airport not found: " + ticket.getDestination());
        }

        OffsetDateTime departureTime = LocalDateTime.of(ticket.getDepartureDate(), ticket.getDepartureTime())
                .atOffset(departureAirport.getOffset());
        OffsetDateTime arrivalTime = LocalDateTime.of(ticket.getArrivalDate(), ticket.getArrivalTime())
                .atOffset(arrivalAirport.getOffset());

        return (int) Duration.between(departureTime, arrivalTime).getSeconds() / TimeUnits.SECONDS_PER_MINUTE.getTimeUnits();
    }

}
