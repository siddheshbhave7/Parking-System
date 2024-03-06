package com.parking.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.parking.Entities.FareCard;

public interface FarecardRepository extends JpaRepository<FareCard,Long>{
	@Modifying
	@Query(value = "update fare_card set fare_charges=? where item_id=1",nativeQuery = true)
	int updateTwoWheelerFare (double fare);
	@Modifying
	@Query(value = "update fare_card set fare_charges=? where item_id=2",nativeQuery = true)
	int updateFourWheelerFare(double fare);
	@Query(value="select fare_charges from fare_card where item_id=?",nativeQuery = true)
	
	Optional<Long> getfareCharge(long vehicletypeId);
		
	Optional<FareCard> findByVehicleType(String vehicleType);
	
}
