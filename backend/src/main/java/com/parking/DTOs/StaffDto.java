package com.parking.DTOs;

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
public class StaffDto {
	private Long staffid;

	private String firstName;

	private String lastName;

	private String email;

	private String mobileNo;
	
	private String address;
	
	private String identityCard;

}
