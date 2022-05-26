package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    //public static final String FILE_PATH = "src/main/resources/tickets.json";
    public static final String ZONE_VLADIVOSTOK = "+10:00";
    public static final String ZONE_TEL_AVIV = "+03:00";
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("HH:mm dd.MM.yy XXX");
    public static final int PERCENTILE = 90;

    public static void main(String[] args) throws IOException {
        System.out.println("Specify the path to the file with the extension .json");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String filePath = bufferedReader.readLine();
        bufferedReader.close();
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File not found");
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();

        Tickets tickets = objectMapper.readValue(file, Tickets.class);


        List<Ticket> ticketsList = tickets.getTickets();

        String averageFlightTime = getAverageFlightTime(ticketsList);
        System.out.println(averageFlightTime);

        String percentileFlightTime = getPercentile(ticketsList, PERCENTILE);
        System.out.println(percentileFlightTime);
    }

    private static String getPercentile(List<Ticket> tickets, int percentile) {
        List<Long> flightTimeAllTickets = flightTimeList(tickets);
        Collections.sort(flightTimeAllTickets);
        int index = (int) Math.ceil(percentile / 100.0 * flightTimeAllTickets.size());
        int percentileInMinutes = flightTimeAllTickets.get(index - 1).intValue();
        int minutes = percentileInMinutes % TimeUnits.MINUTES_PER_HOUR.getTimeUnits();
        int hour = percentileInMinutes / TimeUnits.MINUTES_PER_HOUR.getTimeUnits();
        return PERCENTILE + "-й процентиль времени полета составялет " + hour + " часов " + minutes + " мин.";

    }

    private static List<Long> flightTimeList(List<Ticket> tickets) {
        List<Long> flightTimeAllTickets = new ArrayList<>();
        long destination;
        long origin;
        long flightTimePerMinutes;
        try {
            for (int i = 0; i < tickets.size(); i++) {
                if (tickets.get(i).getOrigin_name().equalsIgnoreCase("Владивосток")
                        && tickets.get(i).getDestination_name().equalsIgnoreCase("Тель-Авив")) {

                    destination = SIMPLE_DATE_FORMAT
                            .parse(tickets.get(i).getArrival_time() + " " + tickets.get(i).getArrival_date() + " " + ZONE_TEL_AVIV).getTime();

                    origin = SIMPLE_DATE_FORMAT
                            .parse(tickets.get(i).getDeparture_time() + " " + tickets.get(i).getDeparture_date() + " " + ZONE_VLADIVOSTOK).getTime();

                    flightTimePerMinutes = (destination - origin) / TimeUnits.MILLISECOND_PER_SECOND.getTimeUnits() / TimeUnits.SECONDS_PER_MINUTE.getTimeUnits();
                    flightTimeAllTickets.add(flightTimePerMinutes);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return flightTimeAllTickets;
    }

    private static String getAverageFlightTime(List<Ticket> tickets) {
        int countTickets = tickets.size();
        int allFLightTimeInMinutes = 0;
        List<Long> averageFlightTime = flightTimeList(tickets);
        for (Long ticketFlightTime : averageFlightTime) {
            allFLightTimeInMinutes += ticketFlightTime.intValue();
        }
        int averageFlightTimesInMinutes = allFLightTimeInMinutes / countTickets;
        int minutes = averageFlightTimesInMinutes % TimeUnits.MINUTES_PER_HOUR.getTimeUnits();
        int hour = averageFlightTimesInMinutes / TimeUnits.MINUTES_PER_HOUR.getTimeUnits();
        return "Среднее время полета составляет " + hour + " часов " + minutes + " мин.";
    }
}
