package org.example.b9routeridemanager.entities;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    MODERATOR,USER,ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}