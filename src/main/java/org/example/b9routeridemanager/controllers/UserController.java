package org.example.b9routeridemanager.controllers;

import lombok.RequiredArgsConstructor;
import org.example.b9routeridemanager.DuplicateEntityException;
import org.example.b9routeridemanager.dto.UserTO;
import org.example.b9routeridemanager.entities.User;
import org.example.b9routeridemanager.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public Set<UserTO> getAllUsers() {
//        return userService.getAllUsers();
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> addUser(@RequestBody UserTO userTO, Authentication authentication){
        try {
            User persistedUser = userService.addNewUser(userTO);
            var location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(persistedUser.getId())
                    .toUri();
            return ResponseEntity.created(location)
                    .body(persistedUser);
        } catch (Exception e) {
            throw new DuplicateEntityException(String.format("User with login %s already exist", userTO.login()));
        }
    }
}
