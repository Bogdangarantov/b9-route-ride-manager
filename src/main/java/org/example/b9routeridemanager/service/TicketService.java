package org.example.b9routeridemanager.service;

import lombok.RequiredArgsConstructor;
import org.example.b9routeridemanager.dto.TicketTO;
import org.example.b9routeridemanager.entities.Route;
import org.example.b9routeridemanager.entities.Ticket;
import org.example.b9routeridemanager.enums.TicketStatus;
import org.example.b9routeridemanager.repositories.RoutesRepository;
import org.example.b9routeridemanager.repositories.TicketRepository;
import org.example.b9routeridemanager.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final UsersRepository usersRepository;
    private final TicketRepository ticketRepository;
    private final RoutesRepository routesRepository;

    public Set<Ticket> getTicketByUserId(Long userId){
        return ticketRepository.getTicketByUserId(userId);
    }

    public Ticket addTicket(TicketTO ticketTO){
        Ticket ticket = mapToTicket(ticketTO);
        return ticketRepository.save(ticket);
    }
    private Ticket mapToTicket(TicketTO ticketTO){
        return Ticket.builder().
                passanger(usersRepository.getUserById(ticketTO.passangerId()))
                .route(ticketTO.routeId())
                .departureCity(ticketTO.departureCity())
                .arrivalCity(ticketTO.arrivalCity())
                .status("Good")
                .seatNumber(ticketTO.seatNumber())
                .dateOfSelling(ticketTO.dateOfSelling())
                .price(ticketTO.price()).build();
    }
}
