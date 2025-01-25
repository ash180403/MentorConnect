package com.cascade.mentorconnect.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import com.cascade.mentorconnect.Entity.Availability;
import com.cascade.mentorconnect.Service.AvailabilityService;

@RestController
@RequestMapping("/api/availability")
@Tag(name = "Availability Management", description = "APIs for managing mentor availability")
public class AvailabilityController {
    @Autowired
    private AvailabilityService availabilityService;

    @GetMapping("/{mentorId}")
    public ResponseEntity<List<Availability>> getAvailability(@PathVariable Long mentorId) {
        return ResponseEntity.ok(availabilityService.getAvailability(mentorId));
    }
}

