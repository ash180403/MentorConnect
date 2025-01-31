package com.cascade.mentorconnect.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.cascade.mentorconnect.Entity.Mentor;
import com.cascade.mentorconnect.Service.MentorService;


@RestController
@RequestMapping("/api/mentors")
// @Tag(name = "Mentor Management", description = "APIs for managing mentors")
public class MentorController {
    @Autowired
    private MentorService mentorService;

    @PostMapping
    public ResponseEntity<Mentor> registerMentor(
            @RequestParam String name,
            @RequestParam String expertise,
            @RequestParam String location,
            @RequestParam String email) {

        Mentor mentor = new Mentor();
        mentor.setName(name);
        mentor.setExpertise(expertise);
        mentor.setLocation(location);
        mentor.setEmail(email);

        return new ResponseEntity<>(mentorService.registerMentor(mentor), HttpStatus.CREATED); 
    }


    @GetMapping("/search")
    public ResponseEntity<List<Mentor>> searchMentors(
            @RequestParam String expertise,
            @RequestParam String location) {
        return ResponseEntity.ok(mentorService.searchMentors(expertise, location));
    }
}

