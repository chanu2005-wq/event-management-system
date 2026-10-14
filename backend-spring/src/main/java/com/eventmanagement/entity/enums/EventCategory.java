package com.eventmanagement.entity.enums;

/**
 * Event classification categories used for search/filter on the
 * {@link com.eventmanagement.entity.Event} entity.
 *
 * <p>Stored as a VARCHAR in the {@code events.category} column.
 */
public enum EventCategory {
    MUSIC,
    SPORTS,
    TECH,
    BUSINESS,
    ART,
    FOOD,
    HEALTH,
    EDUCATION,
    OTHER
}
