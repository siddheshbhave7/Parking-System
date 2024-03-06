package com.parking.DTOs;

import java.time.LocalDateTime;

import com.parking.Entities.Payment;

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
public class SlotBookingDto {
	
	private long bookingId;
	//private long ItemId;
	private long pid;
	private long vehicleId;
	private long userId;
	private int slots;
	private String vehicleNo;
	private String vehicleType;
	private LocalDateTime startTime;
	private LocalDateTime exitTime;
	private int parikingDuration;
	private String bookingStatus;
	private int slotNo;
	private double amount;
	private String parkingName;

	
}
