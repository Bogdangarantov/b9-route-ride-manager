package org.example.b9routeridemanager.repositories;

import org.example.b9routeridemanager.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    @BeforeEach
    void setUp() {
        User user1 = new User();
        user1.setLogin("user1");

        User user2 = new User();
        user2.setLogin("user2");

        usersRepository.save(user1);
        usersRepository.save(user2);
    }

    @Test
    void testFindUserByLogin() {
        Optional<User> user = usersRepository.findUserByLogin("user1");
        assertThat(user).isPresent();
        assertThat(user.get().getLogin()).isEqualTo("user1");
    }

    @Test
    void testGetAll() {
        Set<User> users = usersRepository.getAll();
        assertThat(users).hasSize(2);
    }

    @Test
    void testGetUserById() {
        User user = usersRepository.findAll().iterator().next();
        User foundUser = usersRepository.getUserById(user.getId());
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getLogin()).isEqualTo(user.getLogin());
    }
}
