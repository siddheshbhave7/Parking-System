package com.parking.repository;



import org.springframework.data.jpa.repository.JpaRepository;


import com.parking.Entities.Feedback;

public interface FeedBackRepository extends JpaRepository<Feedback, Long> {
	

}
