package com.parking.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.DTOs.DtoEntityConvertor;
import com.parking.DTOs.SlotBookingDto;
import com.parking.DTOs.UserDto;
import com.parking.DTOs.UserSignUpDto;
import com.parking.Entities.Booking;
import com.parking.Entities.FareCard;
import com.parking.Entities.Parking;
import com.parking.Entities.Payment;
import com.parking.Entities.User;
import com.parking.Entities.Vehicle;
import com.parking.repository.BookingRepository;
import com.parking.repository.FarecardRepository;
import com.parking.repository.ParkingRepository;
import com.parking.repository.PaymentRepository;
import com.parking.repository.UserRepository;
import com.parking.repository.VehicleRepository;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private DtoEntityConvertor conversion;
	@Autowired
	private BookingRepository bookingRepo;
	@Autowired
	private VehicleRepository vehicleRepo;
	@Autowired
	private ParkingRepository parkrepo;
	@Autowired
	private FarecardRepository fareCardRepo;
	@Autowired
	private PaymentRepository paymentRepo;
	

	public UserDto authenticateUser(String email, String password) {
		User authenticatedUser = userRepo.findByEmailAndPassword(email, password);
		UserDto convertedUserDto = conversion.toUserDto(authenticatedUser);
		return convertedUserDto;
	}

	public Map<String, Object> UserSignUpService(UserSignUpDto signUp) {
		User persistUser = conversion.toUserEntity(signUp);
		User user = userRepo.save(persistUser);
		return Collections.singletonMap("peristed", user);
	}

	public String generatedBooking(int slotno, SlotBookingDto addbooking) {

		User user = userRepo.findById(addbooking.getUserId()).orElseThrow(() -> new RuntimeException());
		

		Vehicle vehicle = new Vehicle(addbooking.getVehicleNo(), addbooking.getVehicleType(), user);
		System.out.println(vehicle);
		System.out.println("before saving vehicle");
		Vehicle savedVehicle = vehicleRepo.save(vehicle);
		System.out.println("after saving vehicle");
		Parking parking = parkrepo.findById(addbooking.getPid()).orElseThrow(() -> new RuntimeException());

		LocalDateTime entry = addbooking.getStartTime();
		LocalDateTime exit = addbooking.getExitTime();
		Duration diff = Duration.between(entry, exit);
		int hrs = (int) (Math.ceil(diff.getSeconds()) / 3600);
		Booking booking = new Booking(user, parking, addbooking.getSlots(), vehicle, addbooking.getStartTime(),
				addbooking.getExitTime(), hrs ,"Booked");
		FareCard fareCard = fareCardRepo.findByVehicleType(addbooking.getVehicleType())
				.orElseThrow(() -> new RuntimeException("Vehicle Type not found"));
		Booking savedbooking = bookingRepo.save(booking);
		Payment payment = new Payment(savedbooking, fareCard.getFare() * hrs);
		paymentRepo.save(payment);
		
		parking.setAvailableSlot(parking.getAvailableSlot()-1);
		parkrepo.save(parking);

		return "Done";

	}
//
//	public String generatedBookings(int slotno, SlotBookingDto addbooking) {
//
//		if (userRepo.existsById(addbooking.getUserId())) {
//			User newUser = userRepo.findById(addbooking.getUserId()).orElse(null);
//
//			Booking booking = conversion.toBookingEntity(addbooking);
//			booking.setUser(new User(newUser.getUserId()));
//			Parking newParking = parkrepo.findById(addbooking.getPid()).orElse(null);
//			booking.setParking(new Parking(newParking.getParkingId()));
//
//			Vehicle newVeh = vehicleRepo.findById(addbooking.getVehicleId()).orElse(null);
//			booking.setVehicle(new Vehicle(newVeh.getVehicleId()));
//			System.out.println(booking);
//			booking.setSlotNo(slotno);
//			LocalDateTime entry = addbooking.getStartTime();
//			LocalDateTime exit = addbooking.getExitTime();
//			Duration diff = Duration.between(entry, exit);
//			int hrs = (int) (Math.ceil(diff.getSeconds()) / 3600);
//			booking.setParikingDuration(hrs);
//			Booking persisted = bookingRepo.save(booking);
//
//			return "success";
//		} else
//			return "fail";
//
//	}

	public List<SlotBookingDto> getbookingById(Long userId) {
		List<Booking> bookings = bookingRepo.findByUser_userId(userId);
		return bookings.stream().map(conversion::fromBookingEntity).collect(Collectors.toList());
	}

	public String cancelBooking(Long bookingId) {
		if (bookingRepo.existsById(bookingId)) {
			
			Booking booking = bookingRepo.findById(bookingId).orElse(null);
			int updatedRows = bookingRepo.updateBookingStatus("Cancelled", bookingId);

			if (updatedRows > 0) {
				Parking parking = booking.getParking();
				parking.setAvailableSlot(parking.getAvailableSlot()+1);
				parkrepo.save(parking);
				return "Booking Cancelled Successfully";
			} else {
				return "Failed to Cancel Booking";
			}
		} else {
			return "Booking Not Found";
		}

	}
	
	
}