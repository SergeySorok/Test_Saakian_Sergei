package org.example.ticket;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class TicketsHolder {
    private List<Ticket> tickets;
}
