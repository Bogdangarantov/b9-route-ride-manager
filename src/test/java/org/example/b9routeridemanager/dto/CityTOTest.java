package org.example.b9routeridemanager.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CityTOTest {

    @Test
    void testCityTO() {
        CityTO cityTO = new CityTO("Kyiv");
        assertEquals("Kyiv", cityTO.name());
    }
}
