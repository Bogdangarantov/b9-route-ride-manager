package org.example.b9routeridemanager.dto;

import org.example.b9routeridemanager.entities.City;
import org.example.b9routeridemanager.entities.Route;

import java.time.LocalDate;

public record TicketTO(Long passangerId,
                       Route routeId,
                       City departureCity,
                       City arrivalCity,
                       String ticketStatus,
                       String seatNumber,
                       LocalDate dateOfSelling,
                       Double price) {
}
