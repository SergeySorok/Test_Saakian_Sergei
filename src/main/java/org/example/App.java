package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.repo.FileWorker;
import org.example.service.TicketAnalyticsService;
import org.example.ticket.Tickets;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        FileWorker fileWorker = new FileWorker();
        ObjectMapper objectMapper = new ObjectMapper();
        Tickets tickets = objectMapper.readValue(fileWorker.getFile(), Tickets.class);
        TicketAnalyticsService ticketAnalyticsService = new TicketAnalyticsService(tickets);

        System.out.println(ticketAnalyticsService.averageFlightTime()); //You can pass the point of departure and arrival to the parameters

        System.out.println(ticketAnalyticsService.percentileResult());
    }
}
