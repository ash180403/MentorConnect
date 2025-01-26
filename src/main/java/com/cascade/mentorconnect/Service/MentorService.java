package com.cascade.mentorconnect.Service;

import java.util.List;
import java.util.Optional;
import com.cascade.mentorconnect.Entity.Mentor;
import com.cascade.mentorconnect.Repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Mentor findMentorById(Long mentorId) {
        Optional<Mentor> mentorOpt = mentorRepository.findById(mentorId);
        return mentorOpt.orElse(null);
    }
}
