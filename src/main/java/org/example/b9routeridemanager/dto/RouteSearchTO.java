package org.example.b9routeridemanager.dto;

import org.example.b9routeridemanager.entities.City;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public record RouteSearchTO(Long id,City departureCity, City arrivalCity, Set<City> routesCities, Double price, LocalDateTime departureTime, LocalDateTime arrivalTime) {
}
