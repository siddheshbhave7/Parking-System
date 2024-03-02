package com.parking.DTOs;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class PaymentDto {
	//private long paymentid;
	private long bookingid;
	private double fare;
	private double amount;
	private int slot;
	@CreationTimestamp
	private LocalDateTime paymentDate;
	private long vehicletypeId;
	private String parking;
	private int parikingDuration;
	

}
