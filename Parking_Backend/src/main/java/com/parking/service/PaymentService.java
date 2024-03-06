package com.parking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.DTOs.DtoEntityConvertor;
import com.parking.DTOs.PaymentDto;
import com.parking.DTOs.SlotBookingDto;
import com.parking.Entities.Booking;
import com.parking.Entities.FareCard;
import com.parking.Entities.Payment;
import com.parking.repository.BookingRepository;
import com.parking.repository.FarecardRepository;
import com.parking.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	PaymentRepository paymentRepo;
	
	@Autowired
	FarecardRepository fareRepos;
	
	@Autowired
	BookingRepository bookinRepo;
	
	
//	public PaymentDto getPaymentByBookingId(Long id, String vehicleType) {
//		
//		
//		FareCard fareCard = fareRepos.findByVehicleType(vehicleType).orElseThrow(()->new RuntimeException("No type found"));
//		Payment payment = paymentRepo.getPaymentDetailsWithBookingId(id);
//		
//		DtoEntityConvertor converter = new DtoEntityConvertor();
//		//System.out.println(payment);
//		PaymentDto dto=converter.fromPayment(payment);
//		dto.setFare(fareCard.getFare());
//		return dto;
//	}
	
	
	public SlotBookingDto getBookingInfo(Long bookingId) {
		
		Booking booking  = bookinRepo.findById(bookingId).orElseThrow(()-> new RuntimeException("Booking not found"));
		DtoEntityConvertor converter = new DtoEntityConvertor();
		Payment payment = paymentRepo.findByBooking_BookingId(bookingId).orElseThrow(()->new RuntimeException("Payment not found"));
		
		SlotBookingDto dto= converter.fromBookingEntity(booking);
		dto.setAmount(payment.getAmount());
		return dto;
	}
	
}
