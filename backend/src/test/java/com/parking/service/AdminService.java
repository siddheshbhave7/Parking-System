package com.parking.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.DTOs.AddParkingDto;
import com.parking.DTOs.DtoEntityConvertor;
import com.parking.DTOs.FareCardDto;
import com.parking.DTOs.UserDto;
import com.parking.Entities.FareCard;
import com.parking.Entities.Parking;
import com.parking.Entities.User;
import com.parking.repository.FarecardRepository;
import com.parking.repository.ParkingRepository;
import com.parking.repository.PaymentRepository;
import com.parking.repository.UserRepository;

@Service
@Transactional
public class AdminService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private DtoEntityConvertor conversion;

	@Autowired
	private PaymentRepository paymentRepo;
	
	@Autowired
	private FarecardRepository fareRepo;
	
	@Autowired
	private ParkingRepository parkRepo;

	public List<UserDto> getAllUserList(String role) {

		List<User> allUserList = userRepo.findByUserRole(role);
		return allUserList.stream().map(user -> conversion.toUserDto(user)).collect(Collectors.toList());
	}

	public double totalCollectionByDate(LocalDateTime d1, LocalDateTime d2) {
		Double totalCollection = paymentRepo.findByPaymentDate(d1, d2);
		return totalCollection;
	}
	
	public String updatedfareofTwoWheeler (double fare) {
		int fareupdated = fareRepo.updateTwoWheelerFare(fare);
		if (fareupdated==1) {
			return "Fare for Two Wheeler is Updated Succesfully!!!!" ;
		}
		
		return "Fare Updation for Two Wheeler Failed";
	}

	public String updatedfareofFourWheeler(double fare) {
		
		int fareupdated = fareRepo.updateFourWheelerFare(fare);
		if (fareupdated==1) {
			return "Fare for Four Wheeler is Updated Succesfully!!!!" ;
		}
		
		return "Fare Updation for Four Wheeler Failed";
	}

	public String addParking(AddParkingDto parking) {
		Parking park=conversion.toParking(parking);
		park.setAvailableSlot(parking.getSlots());
		Parking persisted=parkRepo.save(park);
		if(persisted!=null)
		return "New Parking Area is Added Successfully......!!";
		return "Something went wrong while adding new parking. Please try again!!!";
	}

	public List <FareCardDto> getFareDetails() {
		List<FareCard> f1= fareRepo.findAll();
		return f1.stream().map(f2 -> conversion.toFareCardDto(f2)).collect(Collectors.toList());
	}

	public List<AddParkingDto> getAllParkingList() {
		List<Parking> parkArea= parkRepo.findAll();
		return parkArea.stream().map(f2 -> conversion.toParkingDto(f2)).collect(Collectors.toList());
	}
}
 
