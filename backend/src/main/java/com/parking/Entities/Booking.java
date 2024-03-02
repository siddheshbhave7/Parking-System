package com.parking.Entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookingId;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "userId",nullable = false)
	//@Column(name = "id", unique = true, nullable = false)
	private User user;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="parkingId")
	private Parking parking;
	@Column(name = "SlotNumber")
	private int slotNo;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "vId")
	private Vehicle vehicle;
	@Column(name = "EntryTime", updatable = false)
	//@CreationTimestamp
	private LocalDateTime startTime;
	@Column(name = "ExitTime")
	private LocalDateTime exitTime;
	@Column(name="Duration")
    private int parikingDuration;

	@Column(name = "BookingStatus")
	private String bookingStatus;
	
	public Booking(Parking parking) {
		super();
		this.parking = parking;
	}

	public Booking(User user, Parking parking, int slotNo, Vehicle vehicle, LocalDateTime startTime,
			LocalDateTime exitTime, int parikingDuration , String bookingStatus) {
		super();
		this.user = user;
		this.parking = parking;
		this.slotNo = slotNo;
		this.vehicle = vehicle;
		this.startTime = startTime;
		this.exitTime = exitTime;
		this.parikingDuration = parikingDuration;
		this.bookingStatus=bookingStatus;
		
	}

	public Booking(Long bookingId) {
		super();
		this.bookingId = bookingId;
	}
	
	
	
}

