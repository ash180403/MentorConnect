package com.cascade.mentorconnect.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cascade.mentorconnect.Entity.Availability;
import com.cascade.mentorconnect.Repository.AvailabilityRepository;

@Service
public class AvailabilityService {
    @Autowired
    private AvailabilityRepository availabilityRepository;

    public List<Availability> getAvailability(Long mentorId) {
        return availabilityRepository.findByMentorId(mentorId);
    }
}

