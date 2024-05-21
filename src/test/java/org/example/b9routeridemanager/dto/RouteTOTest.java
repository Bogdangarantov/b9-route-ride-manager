package org.example.b9routeridemanager.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.b9routeridemanager.entities.City;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

class RouteTOTest {

    @Test
    void testRouteTO() {
        City city = new City();
        RouteTO routeTO = new RouteTO(city, LocalDateTime.now(), new ArrayList<>(), city, LocalDateTime.now().plusHours(5), 100.0, 50.0);

        assertEquals(city, routeTO.departureCity());
        assertEquals(city, routeTO.arrivalCity());
        assertEquals(100.0, routeTO.userTicketPrice());
        assertEquals(50.0, routeTO.baggageTicketPrice());
    }
}
