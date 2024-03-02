package com.parking.Entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "feedback")
@Getter
@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long feedbackid;

	    @Column(name = "name",length = 20)
	    private String name;
	    @Column(name = "feedback_text",length = 300)
	    private String feedbackText;

	    @Column(name = "Subject",length = 50)
	    private String subject;
	    
	    @Column(name = "email")
	    private String email;

}
