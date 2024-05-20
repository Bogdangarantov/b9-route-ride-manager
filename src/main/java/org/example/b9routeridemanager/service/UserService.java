package org.example.b9routeridemanager.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.b9routeridemanager.dto.UserTO;
import org.example.b9routeridemanager.entities.User;
import org.example.b9routeridemanager.repositories.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    private Set<UserTO> mapUsersToUserTO(Set<User> users) {
        return users.stream().map(user -> new UserTO( user.getId(),user.getName(),user.getRoles(), user.getLogin(), user.getPassword())).collect(Collectors.toSet());
    }
    public Set<UserTO> getAllUsers() {
        return mapUsersToUserTO(usersRepository.getAll());
    }
    private User mapToUser(UserTO userTO) {
        return User.builder()
                .login(userTO.login())
                .roles(userTO.roles())
                .password(passwordEncoder.encode(userTO.password()))
                .name(userTO.name())
                .build();
    }

    @Transactional
    public User addNewUser(UserTO newUser) {
        User user = mapToUser(newUser);
        return usersRepository.save(user);
    }
}
