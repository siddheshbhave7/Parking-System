package com.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.parking.DTOs.PaymentDto;
import com.parking.DTOs.Response;
import com.parking.service.ParkingService;
import com.parking.service.PaymentService;
@CrossOrigin ("*")
@RestController
@RequestMapping("/ParkingPayment")
public class PaymentController {
	@Autowired
	private ParkingService parkService;
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping(value="/getPayment/{userid}") 
	public ResponseEntity<?> generatePayment(@PathVariable("userid") Long user ,@RequestBody PaymentDto pay)
	{
	    String payment = parkService.fetchPayment(user,pay);
		return Response.success(payment);
	}
	
//	@GetMapping(value="/{id}/{type}") 
//	public ResponseEntity<?> generatePaymentDetails(@PathVariable("id")Long id,@PathVariable("type") String type)
//	{
//	   return ResponseEntity.ok(paymentService.getPaymentByBookingId(id, type));
//	}
	
	@GetMapping(value="/booking/{id}") 
	public ResponseEntity<?> getBookinGInfo(@PathVariable Long id)
	{
	   return ResponseEntity.ok(paymentService.getBookingInfo(id));
	}
	
	
	
}
//@DateTimeFormat(pattern= "yyyy-MM-dd HH:mm:ss")