package org.example.b9routeridemanager.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.example.b9routeridemanager.dto.CityTO;
import org.example.b9routeridemanager.entities.City;
import org.example.b9routeridemanager.repositories.CitiesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

class CityServiceTest {

    @InjectMocks
    private CityService cityService;

    @Mock
    private CitiesRepository citiesRepository;

    private City city;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        city = City.builder().id(1L).cityName("Kyiv").build();
    }

    @Test
    void testAddCity() {
        when(citiesRepository.save(city)).thenReturn(city);
        City result = citiesRepository.save(city);
        assertEquals(city, result);
    }

    @Test
    void testGetCityById() {
        when(citiesRepository.findById(1L)).thenReturn(Optional.of(city));
        City result = citiesRepository.getCityById(1L);
        assertEquals(city, result);
    }

    @Test
    void testGetAllCities() {
        ArrayList<City> cities = new ArrayList<>();
        cities.add(city);
        when(citiesRepository.findAll()).thenReturn(cities);
        Set<City> result = cityService.getAllCities();
        assertEquals(cities, result);
    }


    @Test
    void testCityNotFound() {
        when(citiesRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> {
            citiesRepository.getCityById(1L);
        });
    }
}
