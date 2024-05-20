package org.example.b9routeridemanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.b9routeridemanager.entities.City;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record RouteTO(@JsonProperty("departureCity") City departureCity,
                      @JsonProperty("departureTime") LocalDateTime departureTime,
                      @JsonProperty("transitCities") List<City> transitCities,
                      @JsonProperty("arrivalCity") City arrivalCity,
                      @JsonProperty("arrivalTime") LocalDateTime arrivalTime,
                      @JsonProperty("userTicketPrice") Double userTicketPrice,
                      @JsonProperty("baggageTicketPrice") Double baggageTicketPrice

                      ) {
}
