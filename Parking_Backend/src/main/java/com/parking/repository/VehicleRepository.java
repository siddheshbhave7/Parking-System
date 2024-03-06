package com.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parking.Entities.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
	
Vehicle findByVehicleId(Long id);
}
