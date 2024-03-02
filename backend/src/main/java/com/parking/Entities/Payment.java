package com.parking.Entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "payments")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long paymentId;
	
	@OneToOne
	@JoinColumn(name="bookingId")
	private Booking booking;
	
	@Column(name ="amount")
	private double amount;
	
	
	@CreationTimestamp
	@Column(name ="paymentDate",columnDefinition = "TIMESTAMP")
	private LocalDateTime paymentDate;

	public Payment(Booking booking, double amount) {
		super();
		this.booking = booking;
		this.amount = amount;
	}
	
	

}
//select u.id,u.fname,u.lname,u.email,u.mobile,
//b.bookingid,b.slotno,b.start ,b,end,b.duration
//p.city,p.location,p.bookinglist,p.parkingname,p.vehicletype
//p.paymentid,p.amount 
//v.vehicle,v.vehicletype,v.vehicleNo,f.fare
//from user u inner join booking b on u.userid=b.userid
//inner join parking p b.parkid=p.parkid
//inner join payment c b.bookingId=c.bookingId
//inner join vehicle v v.userid=u.userid
//inner join farecard f.vehicletype=v.vehicletype