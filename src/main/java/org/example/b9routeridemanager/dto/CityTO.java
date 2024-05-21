package org.example.b9routeridemanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CityTO(@JsonProperty("name") String name) {
}
