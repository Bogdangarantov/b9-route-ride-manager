package org.example.b9routeridemanager.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.example.b9routeridemanager.dto.CityTO;
import org.example.b9routeridemanager.dto.RouteTO;
import org.example.b9routeridemanager.dto.TicketTO;
import org.example.b9routeridemanager.entities.City;
import org.example.b9routeridemanager.entities.Route;
import org.example.b9routeridemanager.entities.Ticket;
import org.example.b9routeridemanager.service.CityService;
import org.example.b9routeridemanager.service.RouteService;
import org.example.b9routeridemanager.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class RouteControllerTest {

    @InjectMocks
    private RouteController routeController;

    @Mock
    private CityService cityService;

    @Mock
    private RouteService routeService;

    @Mock
    private TicketService ticketService;

    private CityTO cityTO;
    private RouteTO routeTO;
    private TicketTO ticketTO;
    private City city;
    private Route route;
    private Ticket ticket;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cityTO = new CityTO("Kyiv");
        city = City.builder().id(1L).cityName("Kyiv").build();
        routeTO = new RouteTO(city, LocalDateTime.now(), new ArrayList<>(), city, LocalDateTime.now().plusHours(5), 100.0, 50.0);
        route = Route.builder().id(1L).departureCity(city).arrivalCity(city).build();
        ticketTO = new TicketTO(1L, route, city, city, "PURCHASED", "1A", LocalDate.now(), 150.0);
        ticket = Ticket.builder().id(1L).route(route).departureCity(city).arrivalCity(city).build();
    }

    @Test
    void testAddCity() {
        when(cityService.addCity(cityTO)).thenReturn(city);
        City result = routeController.addCity(cityTO);
        assertEquals(city, result);
    }

    @Test
    void testGetAllCities() {
        Set<City> cities = new HashSet<>();
        cities.add(city);
        when(cityService.getAllCities()).thenReturn(cities);
        Set<City> result = routeController.getAllCities();
        assertEquals(cities, result);
    }

    @Test
    void testAddRoute() {
        when(routeService.addRoute(routeTO)).thenReturn(route);
        Route result = routeController.addRoute(routeTO);
        assertEquals(route, result);
    }

    @Test
    void testGetRouteById() {
        when(routeService.getRouteById(1L)).thenReturn(route);
        Route result = routeController.getRouteById(1L);
        assertEquals(route, result);
    }

    @Test
    void testAddTicket() {
        when(ticketService.addTicket(ticketTO)).thenReturn(ticket);
        Ticket result = routeController.addTicket(ticketTO);
        assertEquals(ticket, result);
    }

    @Test
    void testGetTicketsByUserId() {
        Set<Ticket> tickets = new HashSet<>();
        tickets.add(ticket);
        when(ticketService.getTicketByUserId(1L)).thenReturn(tickets);
        Set<Ticket> result = routeController.getTicketsByUserId(1L);
        assertEquals(tickets, result);
    }
}
