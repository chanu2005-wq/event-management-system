package com.eventmanagement.dto;

import com.eventmanagement.entity.User;
import com.eventmanagement.entity.enums.Role;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/** Public user profile — never includes the password hash. */
@Getter
@Builder
public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private String phone;
    private String profileImageUrl;
    private LocalDateTime createdAt;

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .phone(user.getPhone())
                .profileImageUrl(user.getProfileImageUrl())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
