package com.cascade.mentorconnect.Service;

import com.cascade.mentorconnect.Entity.Availability;
import com.cascade.mentorconnect.Entity.Mentor;
import com.cascade.mentorconnect.Repository.AvailabilityRepository;
import com.cascade.mentorconnect.Repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private MentorRepository mentorRepository;

    @Override
    public List<Availability> getAvailability(Long mentorId) {
        return availabilityRepository.findByMentor_Id(mentorId);
    }

    @Override
    public Availability addAvailability(Long mentorId, Availability availability) {
        Optional<Mentor> mentorOpt = mentorRepository.findById(mentorId);
        
        if (mentorOpt.isPresent()) {
            Mentor mentor = mentorOpt.get();
            availability.setMentor(mentor);
            return availabilityRepository.save(availability);
        } else {
            throw new RuntimeException("Mentor not found with ID: " + mentorId);
        }
    }
}
