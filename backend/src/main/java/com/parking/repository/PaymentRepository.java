package com.parking.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import com.parking.Entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

	@Query(value = "select sum(amount)as Amount from payments where payment_Date>=? and payment_Date<=?", nativeQuery = true)
	double findByPaymentDate(LocalDateTime T1, LocalDateTime T2);

	@Modifying
	@Query(value = "insert into payments (amount,payment_date,booking_id) value (?,?,?)", nativeQuery = true)
	int persistPaymentRecord(double amount, LocalDateTime paymentDate, long bookingid);

	@Query(value = "SELECT * FROM payments WHERE booking_id = ?1 ORDER BY payment_date DESC LIMIT 1", nativeQuery = true)
	Payment getPaymentDetailsWithBookingId(long bookingid);
	
	
	Optional<Payment> findByBooking_BookingId(Long bookingId);


}
