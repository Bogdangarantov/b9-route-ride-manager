package org.example.b9routeridemanager.controllers;

import lombok.RequiredArgsConstructor;
import org.example.b9routeridemanager.dto.CityTO;
import org.example.b9routeridemanager.dto.RouteSearchTO;
import org.example.b9routeridemanager.dto.RouteTO;
import org.example.b9routeridemanager.dto.TicketTO;
import org.example.b9routeridemanager.entities.City;
import org.example.b9routeridemanager.entities.Route;
import org.example.b9routeridemanager.entities.Ticket;
import org.example.b9routeridemanager.service.CityService;
import org.example.b9routeridemanager.service.RouteService;
import org.example.b9routeridemanager.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class RouteController {
    @Autowired CityService cityService;
    @Autowired RouteService routeService;
    @Autowired TicketService ticketService;

    @PostMapping("/city")
    @ResponseBody
    public City addCity(@RequestBody CityTO cityTO) {
        return cityService.addCity(cityTO);
    }
    @GetMapping("/city")
    @ResponseBody
    public Set<City> getAllCities(){
        return cityService.getAllCities();
    }

    @PostMapping("/route")
    @ResponseBody
    public Route addRoute(@RequestBody RouteTO routeTO){
       return routeService.addRoute(routeTO);
    }

    @GetMapping("/search")
    @ResponseBody
    public Set<RouteSearchTO> searchTickets(@RequestParam City from,
                                            @RequestParam City to,
                                            @RequestParam LocalDate departure_date,
                                            @RequestParam Integer baggage){
        return routeService.getAllRoutes(from,to,departure_date,baggage);
    }
    @GetMapping("/route/{route_id}")
    @ResponseBody
    public Route getRouteById(@PathVariable(name = "route_id") Long route_id){
        return routeService.getRouteById(route_id);
    }
    @PostMapping("/ticket")
    @ResponseBody
    public Ticket addTicket(@RequestBody TicketTO ticketTO){
        return ticketService.addTicket(ticketTO);
    }
    @GetMapping("/ticket")
    @ResponseBody
    public Set<Ticket> getTicketsByUserId(@RequestParam(name = "user_id") Long userId){
        return ticketService.getTicketByUserId(userId);
    }
}
