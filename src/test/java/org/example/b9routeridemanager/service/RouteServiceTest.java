package org.example.b9routeridemanager.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.example.b9routeridemanager.entities.Route;
import org.example.b9routeridemanager.repositories.RoutesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

class RouteServiceTest {

    @InjectMocks
    private RouteService routeService;

    @Mock
    private RoutesRepository routesRepository;

    private Route route;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        route = Route.builder().id(1L).build();
    }

    @Test
    void testAddRoute() {
        when(routesRepository.save(route)).thenReturn(route);
        Route result = routesRepository.save(route);
        assertEquals(route, result);
    }

    @Test
    void testGetRouteById() {
        when(routesRepository.findById(1L)).thenReturn(Optional.of(route));
        Route result = routeService.getRouteById(1L);
        assertEquals(route, result);
    }

    @Test
    void getAllRoutes() {
    }

    @Test
    void testRouteNotFound() {
        when(routesRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> routeService.getRouteById(1L));
    }
}
