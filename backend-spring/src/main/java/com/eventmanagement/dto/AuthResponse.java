package com.eventmanagement.dto;

import com.eventmanagement.entity.User;
import com.eventmanagement.entity.enums.Role;
import lombok.Builder;
import lombok.Getter;

/** Returned by register and login. */
@Getter
@Builder
public class AuthResponse {

    private String accessToken;
    private String tokenType;
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;

    public static AuthResponse of(String accessToken, User user) {
        return AuthResponse.builder()
                .accessToken(accessToken)
                .tokenType("Bearer")
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
