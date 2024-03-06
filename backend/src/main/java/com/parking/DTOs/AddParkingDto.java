package com.parking.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AddParkingDto {
	private Long parkingId;

	private String city;

	private String location;

	private int slots;

	private String parkingName;
	
	private String vehicletype;
	
	private int availableSlot;

	
}
