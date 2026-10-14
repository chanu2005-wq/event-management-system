package com.eventmanagement.entity.enums;

/**
 * User roles that drive Spring Security access control.
 *
 * <p>Stored as a VARCHAR in the {@code users.role} column via
 * {@code @Enumerated(EnumType.STRING)} in {@link com.eventmanagement.entity.User}.
 *
 * <ul>
 *   <li>{@code ADMIN}     – full system access; can manage all users, events, bookings.</li>
 *   <li>{@code ORGANIZER} – can create and manage their own events.</li>
 *   <li>{@code ATTENDEE}  – can browse events and manage their own bookings.</li>
 * </ul>
 */
public enum Role {
    ADMIN,
    ORGANIZER,
    ATTENDEE
}
