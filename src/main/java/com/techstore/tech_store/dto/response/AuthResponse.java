package com.techstore.tech_store.dto.response;

import com.techstore.tech_store.entity.Role;
import com.techstore.tech_store.entity.User;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class AuthResponse {
    private String message;
    private UUID userId;
    private String email;
    private String fullName;
    private Set<String> roles;

    public AuthResponse(String message, User user) {
        this.message = message;
        this.userId = user.getId();
        this.email = user.getEmail();
        this.fullName = user.getFullName();
        this.roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }

    public String getMessage() {
        return message;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public Set<String> getRoles() {
        return roles;
    }
}
