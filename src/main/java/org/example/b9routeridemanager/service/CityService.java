package org.example.b9routeridemanager.service;

import lombok.RequiredArgsConstructor;
import org.example.b9routeridemanager.dto.CityTO;
import org.example.b9routeridemanager.entities.City;
import org.example.b9routeridemanager.repositories.CitiesRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CitiesRepository citiesRepository;

    private City mapToCity(CityTO cityTO){
        return City.builder().cityName(cityTO.name()).build();
    }
    public City addCity(CityTO cityTO){
        City city = mapToCity(cityTO);
        return citiesRepository.save(city);
    }
    public Set<City> getAllCities(){
        return citiesRepository.getAllCities();
    }
}
