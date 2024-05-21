package org.example.b9routeridemanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.b9routeridemanager.entities.UserRole;

import java.util.Set;

public record UserTO(Long id,@JsonProperty("name") String name, @JsonProperty("roles") Set<UserRole> roles,@JsonProperty("login") String login,@JsonProperty("password") String password) {
}
