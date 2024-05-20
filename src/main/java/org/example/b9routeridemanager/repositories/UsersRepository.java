package org.example.b9routeridemanager.repositories;

import org.example.b9routeridemanager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    @Query("Select u FROM User u join fetch u.roles where u.login = :login")
    Optional<User> findUserByLogin(String login);

    @Query("SELECT u FROM User u")
    Set<User> getAll();
    @Query("SELECT u FROM User u where u.id=:id")
    User getUserById(Long id);

}
