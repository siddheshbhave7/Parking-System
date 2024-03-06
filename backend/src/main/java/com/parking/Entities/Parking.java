package com.parking.Entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Parking")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Parking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long parkingId;
	
	@Column(name = "City", length = 30)
	private String city;
	
	@Column(name = "location", length = 30)
	private String location;
	
	@Column(name = "numberOfSlots")
	private int slots;
	
	@OneToMany(mappedBy = "parking")
	private List<Booking> bookingList;
	
	@Column(name = "ParkName", length = 40,unique=true)
	private String parkingName;
	
	@Column(name = "VehicleType", length = 20)
    private String vehicleType;
	
	@Column(name="Available_Slots")
	private int availableSlot;


	public Parking(Long parkingId) {
		super();
		this.parkingId = parkingId;
	}
}
