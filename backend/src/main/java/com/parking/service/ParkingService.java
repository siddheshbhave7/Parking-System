package com.parking.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.DTOs.DtoEntityConvertor;
import com.parking.DTOs.PaymentDto;
import com.parking.Entities.Booking;
import com.parking.Entities.Payment;
import com.parking.repository.BookingRepository;
import com.parking.repository.FarecardRepository;
import com.parking.repository.PaymentRepository;

@Service
@Transactional
public class ParkingService {

	@Autowired
	private PaymentRepository paymentRepo;
	@Autowired
	private BookingRepository bookingRepo;
	@Autowired
	private FarecardRepository fareRepo;
	@Autowired
	private DtoEntityConvertor conversion;

	public String fetchPayment(Long user,PaymentDto pay) {
		int duration =bookingRepo.getDuration(pay.getBookingid()); 
		System.out.println(duration);
		System.out.println(pay.getVehicletypeId());
	    double fare=fareRepo.getfareCharge(pay.getVehicletypeId()).orElseThrow(()->new RuntimeException("Charge Not Found"));
	    System.out.println("fare" + fare);
	    pay.setAmount(duration*fare);
	    Payment payment = new Payment(new Booking( pay.getBookingid()),pay.getAmount());
	    paymentRepo.save(payment);

	    	return "success";
	}
	

}
