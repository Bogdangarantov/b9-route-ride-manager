package org.example.b9routeridemanager.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.example.b9routeridemanager.entities.Ticket;
import org.example.b9routeridemanager.repositories.TicketRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

class TicketServiceTest {

    @InjectMocks
    private TicketService ticketService;

    @Mock
    private TicketRepositoryTest ticketRepository;

    private Ticket ticket;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ticket = Ticket.builder().id(1L).build();
    }

//    @Test
//    void testAddTicket() {
//        when(ticketService.addTicket(ticket)).thenReturn(ticket);
//        Ticket result = ticketService.addTicket(ticket);
//        assertEquals(ticket, result);
//    }

    @Test
    void testGetTicketById() {
        when(ticketService.getTicketByUserId(1L)).thenReturn((Set<Ticket>) ticket);
        Set<Ticket> result = ticketService.getTicketByUserId(1L);
        assertEquals(ticket, result);
    }

    @Test
    void testGetTicketsByUserId() {
        Set<Ticket> tickets = new HashSet<>();
        tickets.add(ticket);
        when(ticketService.getTicketByUserId(1L)).thenReturn(tickets);
        Set<Ticket> result = ticketService.getTicketByUserId(1L);
        assertEquals(tickets, result);
    }

//    @Test
//    void testTicketNotFound() {
//        when(ticketService.getTicketByUserId(1L)).thenReturn(Optional.empty());
//        assertThrows(RuntimeException.class, () -> ticketService.getTicketByUserId(1L));
//    }
}
