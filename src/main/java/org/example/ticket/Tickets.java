package org.example.ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class Tickets {
    private static final int PERCENTILE = 90;

    private List<Ticket> tickets;



    public int getSize() {
        return tickets.size();
    }

    public int getAverageFlightTimeInMinutes() {
        int allFLightTimeInMinutes = 0;
        for (Ticket ticket : tickets) {
            allFLightTimeInMinutes += ticket.getFlightTime();
        }
        return allFLightTimeInMinutes/tickets.size();
    }

    public int getAverageFlightTimeInMinutes(String departurePoint, String placeOfArrival) {
        int allFLightTimeInMinutes = 0;
        int requestTicketsSize=0;

            for (Ticket ticket : tickets) {
                if (departurePoint.equals(ticket.getOrigin()) && placeOfArrival.equals(ticket.getDestination())) {
                    allFLightTimeInMinutes += ticket.getFlightTime();
                    requestTicketsSize++;
                }
            }
        return allFLightTimeInMinutes/requestTicketsSize;
    }

    public int getPercentileInMinutes() {
        List<Integer> flightTimeAllTickets = getAllFlightTime();
        Collections.sort(flightTimeAllTickets);

        int index = (int) Math.ceil(PERCENTILE / 100.0 * flightTimeAllTickets.size());
        return flightTimeAllTickets.get(index - 1);
    }

    private List<Integer> getAllFlightTime() {
        List<Integer> allFlightTime = new ArrayList<>();
        for (Ticket ticket:this.tickets) {
            allFlightTime.add(ticket.getFlightTime());
        }
        return allFlightTime;
    }

}
