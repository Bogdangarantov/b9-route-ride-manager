package org.example.b9routeridemanager.service;

import lombok.RequiredArgsConstructor;
import org.example.b9routeridemanager.dto.RouteSearchTO;
import org.example.b9routeridemanager.dto.RouteTO;
import org.example.b9routeridemanager.entities.City;
import org.example.b9routeridemanager.entities.Route;
import org.example.b9routeridemanager.entities.Ticket;
import org.example.b9routeridemanager.repositories.CitiesRepository;
import org.example.b9routeridemanager.repositories.RoutesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RoutesRepository routesRepository;
    private final CitiesRepository citiesRepository;



    public Route getRouteById(Long route_id){
        return routesRepository.getRouteById(route_id);
    }
    public Route addRoute(RouteTO routeTO){
        Route route = mapToRoute(routeTO);
        return routesRepository.saveAndFlush(route);
    }
    private Route mapToRoute(RouteTO routeTO){
        Route route = Route
                .builder()
                .departureCity(routeTO.departureCity())
                .arrivalCity(routeTO.arrivalCity())
                .departureTime(routeTO.departureTime())
                .arrivalTime(routeTO.arrivalTime())
                .userTicketPrice(routeTO.userTicketPrice())
                .baggageTicketPrice(routeTO.baggageTicketPrice())
                .cities(new HashSet<>(routeTO.transitCities()))
                .build();
        return route;

    }

    public Set<RouteSearchTO> getAllRoutes(City from, City to, LocalDate departureDate, Integer baggage) {
        Set<Route> routes = routesRepository.getAllRoutes();
        Set<Route> foundRoutes = routes.stream()
                .filter(route -> {
                    System.out.println(route.getDepartureTime().toLocalDate().equals(departureDate));
                    return route.getDepartureTime().toLocalDate().equals(departureDate);
                })
                .filter(route -> {
                    System.out.println(routePassesThroughCities(route, from, to));
                    return routePassesThroughCities(route, from, to);
                }) // Фільтрація за проходженням через міста
                .collect(Collectors.toSet());
        Set<RouteSearchTO> routeSearchTOS = new HashSet<>();
        foundRoutes.stream().forEach(route -> {
            Double price = route.getBaggageTicketPrice() + route.getBaggageTicketPrice()*baggage;
            RouteSearchTO searchTO = new RouteSearchTO(route.getId(),route.getDepartureCity(),route.getArrivalCity(),route.getCities(),price,route.getDepartureTime(),route.getArrivalTime());
            routeSearchTOS.add(searchTO);
        });
        return routeSearchTOS;
    }

    private boolean routePassesThroughCities(Route route, City from, City to) {
        if (route.getDepartureCity().equals(from)){
            if (route.getArrivalCity().equals(to)){
                return true;
            }else{
                List<City> cities = new ArrayList<>(route.getCities());
                for (City city : cities) {
                    if (city.equals(to)) {
                        return true;
                    }
                }
            }
        }else{
            if (route.getArrivalCity().equals(to)){
                List<City> cities = new ArrayList<>(route.getCities());
                for (City city : cities) {
                    if (city.equals(from)) {
                        return true;
                    }
                }
            }else{
                List<City> cities = new ArrayList<>(route.getCities());
                int fromIndex = -1;
                int toIndex = -1;

                for (int i = 0; i < cities.size(); i++) {
                    if (cities.get(i).equals(from)) {
                        fromIndex = i;
                    }
                    if (cities.get(i).equals(to)) {
                        toIndex = i;
                    }
                    if (fromIndex != -1 && toIndex != -1) {
                        return true;
                    }
                }
            }
        }


        return false;
    }

}
