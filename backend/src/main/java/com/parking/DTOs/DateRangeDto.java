package com.parking.DTOs;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

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

public class DateRangeDto {
	@DateTimeFormat(pattern= "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime initialDate;
	@DateTimeFormat(pattern= "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endDate;
	
	
	
}
