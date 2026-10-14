package com.eventmanagement.dto;

import com.eventmanagement.entity.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Body for {@code POST /api/auth/register}.
 *
 * <p>Self-registration is limited to {@link Role#ATTENDEE} (default) or
 * {@link Role#ORGANIZER}. {@link Role#ADMIN} accounts are seed-only.
 */
@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be 2–50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be 2–50 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid email address")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 72, message = "Password must be 8–72 characters")
    private String password;

    @Size(max = 20, message = "Phone must be at most 20 characters")
    private String phone;

    /** Optional. Defaults to ATTENDEE. ADMIN is rejected in the service. */
    private Role role;
}
