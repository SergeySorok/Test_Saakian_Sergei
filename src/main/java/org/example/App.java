package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.service.TicketAnalyticsService;
import org.example.ticket.TicketsHolder;
import org.example.util.DateTimeFormatUtil;

import java.io.*;

public class App {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().registerModule(new JavaTimeModule());
    private static final int DEFAULT_PERCENTILE = 90;
    private static final String DEFAULT_ORIGIN = "VVO";
    private static final String DEFAULT_DESTINATION = "TLV";

    public static void main(String[] args) throws IOException {
        String fileName = requestFileName();
        TicketsHolder ticketsHolder = readTicketsFromFile(fileName);


        int averageFlightTimeInMinutes = TicketAnalyticsService.getAverageFlightTimeInMinutes(ticketsHolder.getTickets(),
                DEFAULT_ORIGIN,
                DEFAULT_DESTINATION);
        printAverageFlightTime(averageFlightTimeInMinutes);

        int percentileInMinutes = TicketAnalyticsService.getPercentileInMinutes(ticketsHolder.getTickets(),
                DEFAULT_PERCENTILE,
                DEFAULT_ORIGIN,
                DEFAULT_DESTINATION);
        printPercentileResult(percentileInMinutes);
    }

    private static String requestFileName() throws IOException {
        System.out.println("Specify the path to the file with the extension .json");
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            return bufferedReader.readLine();
        }
    }

    private static TicketsHolder readTicketsFromFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found");
        }
        return OBJECT_MAPPER.readValue(file, TicketsHolder.class);
    }

    public static void printPercentileResult(int minutes) {
        System.out.println("The flight time percentile is " + DateTimeFormatUtil.minutesToPrettyString(minutes));
    }

    public static void printAverageFlightTime(int minutes) {
        System.out.println("The average flight time is " + DateTimeFormatUtil.minutesToPrettyString(minutes));
    }
}
