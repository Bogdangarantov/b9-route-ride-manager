package org.example.b9routeridemanager.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.example.b9routeridemanager.DuplicateEntityException;
import org.example.b9routeridemanager.dto.UserTO;
import org.example.b9routeridemanager.entities.User;
import org.example.b9routeridemanager.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.Set;

class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private Authentication authentication;

    private UserTO userTO;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userTO = new UserTO(1L, "John", Set.of(), "john", "password");
        user = User.builder().id(1L).name("John").login("john").build();
    }

    @Test
    void testAddUser() {
        when(userService.addNewUser(userTO)).thenReturn(user);
        ResponseEntity<User> response = userController.addUser(userTO, authentication);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(user, response.getBody());
    }

    @Test
    void testAddUserDuplicate() {
        when(userService.addNewUser(userTO)).thenThrow(new DuplicateEntityException("User with login john already exist"));
        assertThrows(DuplicateEntityException.class, () -> {
            userController.addUser(userTO, authentication);
        });
    }
}
