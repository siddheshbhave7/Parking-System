package com.parking.Entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="vehicle_details")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private  Long vehicleId;
	
	@Column(name="vehicleNo", length=20 )
	private String vehicleNo;
	
	@Column(name="vehicleType",length=20 )
	private String vehicleType;
	
	@ManyToOne()
	@JoinColumn(name="userId")
	private User user;
	
	public Vehicle(Long vehicleId) {
		super();
		this.vehicleId = vehicleId;
	
	}

	public Vehicle(String vehicleNo, String vehicleType, User user) {
		super();
		this.vehicleNo = vehicleNo;
		this.vehicleType = vehicleType;
		this.user = user;
	}
	
	

}
