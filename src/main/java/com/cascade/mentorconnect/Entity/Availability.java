package com.cascade.mentorconnect.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date; // Date of availability
    private LocalTime startTime; // Start time of availability
    private LocalTime endTime; // End time of availability

    @ManyToOne
    @JoinColumn(name = "mentor_id", nullable = false)
    private Mentor mentor; // Link to the Mentor entity
}
