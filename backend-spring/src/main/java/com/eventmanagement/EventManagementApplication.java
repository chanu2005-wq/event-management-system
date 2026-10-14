package com.eventmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Entry point for the Event Management Spring Boot application.
 *
 * <p>@EnableJpaAuditing enables automatic population of @CreatedDate and
 * @LastModifiedDate fields on all JPA entities.
 */
@SpringBootApplication
@EnableJpaAuditing
public class EventManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventManagementApplication.class, args);
    }
}
