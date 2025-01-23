package com.cascade.mentorconnect.Repository;

import com.cascade.mentorconnect.Entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRepository extends jpaRepository<Mentor, Long> {
    List<Mentor> findByExpertiseAndLocation(String expertise, String location);
}
    @Repository
    public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
        List<Availability> findByMentorId(Long mentorId);
    }

