package com.cascade.mentorconnect.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cascade.mentorconnect.Entity.Mentor;
import com.cascade.mentorconnect.Repository.MentorRepository;


@Service
public class MentorService {
    @Autowired
    private MentorRepository mentorRepository;

    public Mentor registerMentor(Mentor mentor) {
        return mentorRepository.save(mentor);
    }

    public List<Mentor> searchMentors(String expertise, String location) {
        return mentorRepository.findByExpertiseAndLocation(expertise, location);
    }
}
