package com.cascade.mentorconnect.Repository;

import com.cascade.mentorconnect.Entity.Mentor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {
	List<Mentor> findByExpertiseAndLocation(String expertise, String location);
	}
    

