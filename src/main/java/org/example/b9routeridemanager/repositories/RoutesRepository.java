package org.example.b9routeridemanager.repositories;

import org.example.b9routeridemanager.entities.Route;
import org.example.b9routeridemanager.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoutesRepository extends JpaRepository<Route, Long> {

    @Query("select r from Route r")
    Set<Route> getAllRoutes();

    @Query("select r from Route r where r.id=:id")
    Route getRouteById(Long id);
}
