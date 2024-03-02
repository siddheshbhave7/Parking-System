package com.parking.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "FareCard")
@Getter
@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class FareCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ItemId;
	
	@Column(name = "VehicleType",length=20,unique=true)
	private String vehicleType;
	
	@Column(name = "fareCharges")
	private double fare;

}
