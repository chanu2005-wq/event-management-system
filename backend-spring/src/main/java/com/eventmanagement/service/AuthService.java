package com.eventmanagement.service;

import com.eventmanagement.dto.AuthResponse;
import com.eventmanagement.dto.LoginRequest;
import com.eventmanagement.dto.RegisterRequest;
import com.eventmanagement.dto.UserResponse;
import com.eventmanagement.entity.User;
import com.eventmanagement.entity.enums.Role;
import com.eventmanagement.exception.ApiException;
import com.eventmanagement.exception.DuplicateResourceException;
import com.eventmanagement.repository.UserRepository;
import com.eventmanagement.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        String email = request.getEmail().trim().toLowerCase();
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateResourceException("An account with this email already exists");
        }

        Role role = request.getRole() == null ? Role.ATTENDEE : request.getRole();
        if (role == Role.ADMIN) {
            throw new ApiException(HttpStatus.FORBIDDEN, "Cannot self-register as ADMIN");
        }

        User user = User.builder()
                .firstName(request.getFirstName().trim())
                .lastName(request.getLastName().trim())
                .email(email)
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .phone(request.getPhone())
                .enabled(true)
                .accountNonLocked(true)
                .build();

        userRepository.save(user);
        return AuthResponse.of(jwtService.generateToken(user), user);
    }

    public AuthResponse login(LoginRequest request) {
        String email = request.getEmail().trim().toLowerCase();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, request.getPassword()));
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ApiException(HttpStatus.UNAUTHORIZED, "Invalid email or password"));
        return AuthResponse.of(jwtService.generateToken(user), user);
    }

    public UserResponse me(User user) {
        return UserResponse.from(user);
    }
}
