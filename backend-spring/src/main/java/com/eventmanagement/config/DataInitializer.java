package com.eventmanagement.config;

import com.eventmanagement.entity.User;
import com.eventmanagement.entity.enums.Role;
import com.eventmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Seeds one user per role so Auth can be tested without registering first.
 * Passwords are BCrypt-hashed. H2 is in-memory, so this runs on every start.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        seed("admin@example.com", "Admin", "User", "Admin@123", Role.ADMIN);
        seed("organizer@example.com", "Organizer", "User", "Organizer@123", Role.ORGANIZER);
        seed("attendee@example.com", "Attendee", "User", "Attendee@123", Role.ATTENDEE);
    }

    private void seed(String email, String firstName, String lastName, String rawPassword, Role role) {
        if (userRepository.existsByEmail(email)) {
            return;
        }
        userRepository.save(User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(passwordEncoder.encode(rawPassword))
                .role(role)
                .enabled(true)
                .accountNonLocked(true)
                .build());
        log.info("Seeded {} user: {}", role, email);
    }
}
