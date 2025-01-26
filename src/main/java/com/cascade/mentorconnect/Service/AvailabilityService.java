package com.cascade.mentorconnect.Service;

import com.cascade.mentorconnect.Entity.Availability;
import java.util.List;

public interface AvailabilityService {
    List<Availability> getAvailability(Long mentorId);
    Availability addAvailability(Long mentorId, Availability availability);  
}