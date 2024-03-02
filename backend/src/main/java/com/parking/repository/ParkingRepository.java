package com.parking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.parking.Entities.Parking;

public interface ParkingRepository extends JpaRepository<Parking,Long>{
	@Query(value= "insert into parking (city,location,park_name,number_of_slots) values (?,?,?,?)",nativeQuery = true)
	Parking insertNewParkingArea(String city, String location, String parkingName, int slots);
	
	Parking findByParkingId(long id);
	
}
