package com.eventmanagement.entity;

import com.eventmanagement.entity.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * JPA entity representing an application user.
 *
 * <p>Implements {@link UserDetails} so Spring Security can use it directly
 * in authentication without a wrapper class.
 *
 * <p>Mapped to the {@code users} table in PostgreSQL.
 *
 * <p>Mongoose equivalent: User model with name, email, password, role fields.
 */
@Entity
@Table(
    name = "users",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_users_email", columnNames = "email")
    }
)
@EntityListeners(AuditingEntityListener.class)   // enables @CreatedDate / @LastModifiedDate
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    // ── Primary Key ────────────────────────────────────────────────────────
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_id_seq", allocationSize = 1)
    private Long id;

    // ── Core Fields ────────────────────────────────────────────────────────

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be 2–50 characters")
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be 2–50 characters")
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid email address")
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    /**
     * BCrypt-hashed password. Plain text is NEVER stored.
     * The raw password is hashed in {@code AuthService} before saving.
     */
    @NotBlank(message = "Password is required")
    @Column(name = "password", nullable = false)
    private String password;

    // ── Role ───────────────────────────────────────────────────────────────
    /**
     * User role stored as a VARCHAR (e.g., "ADMIN", "ORGANIZER", "ATTENDEE").
     * Defaults to ATTENDEE for self-registration.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 20)
    @Builder.Default
    private Role role = Role.ATTENDEE;

    // ── Account State ──────────────────────────────────────────────────────
    @Column(name = "enabled", nullable = false)
    @Builder.Default
    private boolean enabled = true;

    @Column(name = "account_non_locked", nullable = false)
    @Builder.Default
    private boolean accountNonLocked = true;

    // ── Profile ────────────────────────────────────────────────────────────
    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    // ── Timestamps (auto-managed by JPA Auditing) ─────────────────────────
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // ── Relationships ──────────────────────────────────────────────────────

    /**
     * Events organised by this user (only meaningful when role = ORGANIZER).
     * Cascade: if a user (organizer) is deleted, their events are also deleted.
     */
    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Event> organizedEvents;

    /**
     * All bookings made by this user.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Booking> bookings;

    // ── UserDetails interface ──────────────────────────────────────────────

    /**
     * Returns a single authority in the format "ROLE_ADMIN", "ROLE_ORGANIZER", etc.
     * Spring Security uses this prefix convention for role-based checks.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    /** Spring Security uses email as the unique username. */
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    // ── Utility ───────────────────────────────────────────────────────────

    /** Convenience method for display purposes. */
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
