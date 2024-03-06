package com.parking.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="User_tbl")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="UserID")
	private Long userId;
	@Column(name="fname",length=30)
	private String firstName;
	@Column(name="lname",length=40)
	private String LastName;
	@Column(name="email",length=40,unique=true)
	private String email;
	@Column(name="password",length=100)
	private String password;
	@Column(name="mobile",length=20,unique=true)
	private String mobileNo;
	@Column(name="UserRole",length=20)
	private String userRole;
	//===============================================
	@Column(name = "identity_card",length=20)
	@Pattern(regexp = "\\d{12}|[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "Invalid Identity Card format")
	private String identityCard;
	//===============================================
	
	public User(Long userId) {
		super();
		this.userId = userId;
	}
	
	
	
}


