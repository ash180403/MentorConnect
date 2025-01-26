package com.cascade.mentorconnect.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.cascade.mentorconnect.Entity.Availability;
import com.cascade.mentorconnect.Entity.Mentor;
import com.cascade.mentorconnect.Service.AvailabilityService;
import com.cascade.mentorconnect.Service.MentorService;

@RestController
@RequestMapping("/api/availability")
@Tag(name = "Availability Management", description = "APIs for managing mentor availability")
public class AvailabilityController {

    @Autowired
    private AvailabilityService availabilityService;

    @Autowired
    private MentorService mentorService;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    @GetMapping("/{mentorId}/checkAvailability")
    public ResponseEntity<String> checkAvailability(
            @PathVariable Long mentorId,
            @RequestParam String date,
            @RequestParam String time) {

        try {
            LocalDate parsedDate = LocalDate.parse(date, DATE_FORMATTER);
            LocalTime parsedTime = LocalTime.parse(time, TIME_FORMATTER);

            Mentor mentor = mentorService.findMentorById(mentorId);
            if (mentor == null) {
                return new ResponseEntity<>("Mentor not found.", HttpStatus.NOT_FOUND);
            }

            List<Availability> availabilities = availabilityService.getAvailability(mentorId);
            for (Availability availability : availabilities) {
                if (!parsedDate.isBefore(availability.getStartDate()) && 
                    !parsedDate.isAfter(availability.getEndDate())) {

                    if (!parsedTime.isBefore(availability.getStartTime()) && 
                        !parsedTime.isAfter(availability.getEndTime())) {
                        return ResponseEntity.ok("Mentor is available.");
                    }
                }
            }

            return new ResponseEntity<>("Mentor is not available at the given time.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid date or time format. Please use 'dd/MM/yyyy' for date and 'HH:mm' for time.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> addAvailability(
            @RequestParam Long mentorId,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam String startTime,
            @RequestParam String endTime) {

        try {
            LocalDate parsedStartDate = LocalDate.parse(startDate, DATE_FORMATTER);
            LocalDate parsedEndDate = LocalDate.parse(endDate, DATE_FORMATTER);
            LocalTime parsedStartTime = LocalTime.parse(startTime, TIME_FORMATTER);
            LocalTime parsedEndTime = LocalTime.parse(endTime, TIME_FORMATTER);

            if (parsedEndDate.isBefore(parsedStartDate)) {
                return new ResponseEntity<>("End date must be after start date.", HttpStatus.BAD_REQUEST);
            }

            Mentor mentor = mentorService.findMentorById(mentorId);
            if (mentor == null) {
                return new ResponseEntity<>("Mentor not found.", HttpStatus.NOT_FOUND);
            }

            Availability availability = new Availability();
            availability.setStartDate(parsedStartDate);
            availability.setEndDate(parsedEndDate);
            availability.setStartTime(parsedStartTime);
            availability.setEndTime(parsedEndTime);
            availability.setMentor(mentor);  

            Availability savedAvailability = availabilityService.addAvailability(mentorId, availability);
            return new ResponseEntity<>(savedAvailability, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid date or time format. Please use 'dd/MM/yyyy' for date and 'HH:mm' for time.", HttpStatus.BAD_REQUEST);
        }
    }
}

