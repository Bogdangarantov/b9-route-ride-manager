package org.example.b9routeridemanager.service;

import lombok.RequiredArgsConstructor;
import org.example.b9routeridemanager.entities.User;
import org.example.b9routeridemanager.repositories.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsersRepository usersRepository;
    private final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        System.out.println(login);
        User user = usersRepository.findUserByLogin(login).orElseThrow(() -> new UsernameNotFoundException("Could not find user"));
        System.out.println(user);
        return user;

    }
}
