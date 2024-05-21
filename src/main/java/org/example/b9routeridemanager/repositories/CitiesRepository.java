package org.example.b9routeridemanager.repositories;

import org.example.b9routeridemanager.entities.City;
import org.example.b9routeridemanager.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CitiesRepository extends JpaRepository<City, Long> {
    @Query("SELECT c FROM City c")
    Set<City> getAllCities();

    @Query("SELECT c FROM City c where c.id=:id")
    City getCityById(Long id);
}
