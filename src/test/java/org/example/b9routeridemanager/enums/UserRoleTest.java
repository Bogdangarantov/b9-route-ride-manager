package org.example.b9routeridemanager.enums;

import static org.junit.jupiter.api.Assertions.*;
import org.example.b9routeridemanager.entities.UserRole;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.EnumSet;

class UserRoleTest {

    @Test
    void testUserRoleValues() {
        UserRole[] roles = UserRole.values();
        assertEquals(2, roles.length);
        assertTrue(EnumSet.of(UserRole.USER, UserRole.ADMIN).containsAll(Arrays.asList(roles)));
    }

    @Test
    void testUserRoleValueOf() {
        assertEquals(UserRole.USER, UserRole.valueOf("USER"));
        assertEquals(UserRole.ADMIN, UserRole.valueOf("ADMIN"));
    }
}
