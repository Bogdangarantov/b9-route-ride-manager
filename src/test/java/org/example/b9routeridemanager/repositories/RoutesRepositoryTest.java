package org.example.b9routeridemanager.repositories;

import org.example.b9routeridemanager.entities.Route;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class RoutesRepositoryTest {

    @Autowired
    private RoutesRepository routesRepository;

    @BeforeEach
    void setUp() {
        Route route1 = new Route();
        // Set route1 fields

        Route route2 = new Route();
        // Set route2 fields

        routesRepository.save(route1);
        routesRepository.save(route2);
    }

    @Test
    void testGetAllRoutes() {
        Set<Route> routes = routesRepository.getAllRoutes();
        assertThat(routes).hasSize(2);
    }

    @Test
    void testGetRouteById() {
        Route route = routesRepository.findAll().iterator().next();
        Route foundRoute = routesRepository.getRouteById(route.getId());
        assertThat(foundRoute).isNotNull();
        // Add more assertions based on route fields
    }
}
