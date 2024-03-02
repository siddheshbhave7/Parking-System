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
public class UserDto {
	private Long userId;
	
	private String firstName;

	private String LastName;
	
	private String email;
		
	private String mobileNo;
	
	private String userRole;
	
	private String identityCard;
}
