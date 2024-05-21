package org.example.b9routeridemanager.repositories;

import org.example.b9routeridemanager.entities.City;
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
public class CitiesRepositoryTest {

    @Autowired
    private CitiesRepository citiesRepository;

    @BeforeEach
    void setUp() {
        City city1 = new City();
        city1.setCityName("City1");

        City city2 = new City();
        city2.setCityName("City2");

        citiesRepository.save(city1);
        citiesRepository.save(city2);
    }

    @Test
    void testGetAllCities() {
        Set<City> cities = citiesRepository.getAllCities();
        assertThat(cities).hasSize(2);
    }

    @Test
    void testGetCityById() {
        City city = citiesRepository.findAll().iterator().next();
        City foundCity = citiesRepository.getCityById(city.getId());
        assertThat(foundCity).isNotNull();
        assertThat(foundCity.getCityName()).isEqualTo(city.getCityName());
    }
}
