package org.example.b9routeridemanager.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.b9routeridemanager.entities.UserRole;
import org.junit.jupiter.api.Test;

import java.util.Set;

class UserTOTest {

    @Test
    void testUserTO() {
        UserTO userTO = new UserTO(1L, "John", Set.of(UserRole.ADMIN), "john", "password");

        assertEquals(1L, userTO.id());
        assertEquals("John", userTO.name());
        assertEquals("john", userTO.login());
        assertEquals("password", userTO.password());
        assertEquals(Set.of(UserRole.ADMIN), userTO.roles());
    }
}
