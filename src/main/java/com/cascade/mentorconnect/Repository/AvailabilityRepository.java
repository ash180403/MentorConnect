package com.cascade.mentorconnect.Repository;
import com.cascade.mentorconnect.Entity.Availability;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    List<Availability> findByMentor_Id(Long mentorId);
//    Availability addAvailability(Long mentorId, Availability availability);
}