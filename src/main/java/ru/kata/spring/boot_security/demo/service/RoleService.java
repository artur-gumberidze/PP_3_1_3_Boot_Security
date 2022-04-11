package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.GrantedAuthority;

public class RoleService implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return null;
    }
}
