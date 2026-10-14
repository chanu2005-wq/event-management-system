package com.eventmanagement.entity;

import com.eventmanagement.entity.enums.EventCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * JPA entity representing an event in the system.
 *
 * <p>Mapped to the {@code events} table in PostgreSQL.
 *
 * <p>Relationships:
 * <ul>
 *   <li>{@code organizer} – ManyToOne → users (who created this event)</li>
 *   <li>{@code bookings}  – OneToMany → bookings (attendees who booked)</li>
 * </ul>
 *
 * <p>Mongoose equivalent: Event model with title, description, date, venue,
 * capacity, price, category, image fields.
 */
@Entity
@Table(
    name = "events",
    indexes = {
        @Index(name = "idx_events_category",       columnList = "category"),
        @Index(name = "idx_events_event_date",     columnList = "event_date"),
        @Index(name = "idx_events_organizer_id",   columnList = "organizer_id"),
        @Index(name = "idx_events_is_published",   columnList = "is_published")
    }
)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

    // ── Primary Key ────────────────────────────────────────────────────────
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_seq")
    @SequenceGenerator(name = "event_seq", sequenceName = "event_id_seq", allocationSize = 1)
    private Long id;

    // ── Core Fields ────────────────────────────────────────────────────────

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 150, message = "Title must be 3–150 characters")
    @Column(name = "title", nullable = false, length = 150)
    private String title;

    @NotBlank(message = "Description is required")
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    /**
     * When the event takes place — stored as TIMESTAMP WITHOUT TIME ZONE.
     * Use UTC on the server and convert to local time on the frontend.
     */
    @NotNull(message = "Event date is required")
    @Future(message = "Event date must be in the future")
    @Column(name = "event_date", nullable = false)
    private LocalDateTime eventDate;

    @NotBlank(message = "Venue is required")
    @Size(max = 200, message = "Venue must be at most 200 characters")
    @Column(name = "venue", nullable = false, length = 200)
    private String venue;

    /** City where the event takes place — used for location-based filtering. */
    @Column(name = "city", length = 100)
    private String city;

    // ── Capacity & Pricing ─────────────────────────────────────────────────

    @NotNull(message = "Capacity is required")
    @Min(value = 1, message = "Capacity must be at least 1")
    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    /**
     * Number of seats still available.
     * Decremented on CONFIRMED booking, incremented on CANCELLED booking.
     * Initialised to {@code capacity} when the event is created.
     */
    @Column(name = "available_seats", nullable = false)
    private Integer availableSeats;

    /**
     * Ticket price. Use {@code 0.00} for free events.
     * Precision(10,2) supports prices up to 99,999,999.99.
     */
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.00", message = "Price cannot be negative")
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    // ── Category ───────────────────────────────────────────────────────────

    @NotNull(message = "Category is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false, length = 20)
    private EventCategory category;

    // ── Image ──────────────────────────────────────────────────────────────

    /**
     * Relative URL of the uploaded event image, e.g. {@code /uploads/event-123.jpg}.
     * Served as a static resource by Spring Boot.
     */
    @Column(name = "image_url")
    private String imageUrl;

    // ── Status ─────────────────────────────────────────────────────────────

    /**
     * Only published events are visible to ATTENDEE users.
     * ORGANIZER/ADMIN can see drafts.
     */
    @Column(name = "is_published", nullable = false)
    @Builder.Default
    private boolean isPublished = false;

    @Column(name = "is_cancelled", nullable = false)
    @Builder.Default
    private boolean isCancelled = false;

    // ── Timestamps ─────────────────────────────────────────────────────────

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // ── Relationships ──────────────────────────────────────────────────────

    /**
     * The organizer who created this event.
     * EAGER fetch — organizer name is always needed when displaying an event.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organizer_id", nullable = false,
                foreignKey = @ForeignKey(name = "fk_events_organizer"))
    private User organizer;

    /**
     * All bookings for this event.
     * LAZY — only loaded when explicitly accessed (e.g., admin dashboard).
     */
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Booking> bookings;

    // ── Utility ───────────────────────────────────────────────────────────

    /** Returns true if at least one seat is available. */
    public boolean hasAvailableSeats() {
        return availableSeats != null && availableSeats > 0;
    }

    /** Returns the number of confirmed bookings. */
    public int getBookedSeats() {
        return capacity - (availableSeats != null ? availableSeats : 0);
    }
}
