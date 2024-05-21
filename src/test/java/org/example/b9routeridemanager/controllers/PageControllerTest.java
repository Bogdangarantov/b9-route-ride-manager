package org.example.b9routeridemanager.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.google.gson.Gson;
import org.example.b9routeridemanager.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

class PageControllerTest {

    @InjectMocks
    private PageController pageController;

    @Mock
    private Gson gson;

    @Mock
    private Model model;

    @Mock
    private Authentication authentication;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = User.builder().id(1L).name("John").login("john").build();
        when(authentication.getPrincipal()).thenReturn(user);
        when(gson.toJson(user)).thenReturn("{\"id\":1,\"name\":\"John\",\"login\":\"john\"}");
    }

    @Test
    void testStartPage() {
        String viewName = pageController.startPage(model, authentication);
        assertEquals("index", viewName);
    }

    @Test
    void testSearch() {
        String viewName = pageController.search("Kyiv", "Lviv", "2024-05-20", 2, model, authentication);
        assertEquals("search", viewName);
    }

    @Test
    void testLogin() {
        String viewName = pageController.login();
        assertEquals("login", viewName);
    }

    @Test
    void testManagement() {
        String viewName = pageController.management(model, authentication);
        assertEquals("management", viewName);
    }

    @Test
    void testPayment() {
        String viewName = pageController.payment("123", "500", model, authentication);
        assertEquals("payment", viewName);
    }

    @Test
    void testMyTickets() {
        String viewName = pageController.myTickets(model, authentication);
        assertEquals("myTickets", viewName);
    }
}
