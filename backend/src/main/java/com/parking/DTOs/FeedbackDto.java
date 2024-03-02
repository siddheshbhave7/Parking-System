package com.parking.DTOs;

import java.time.LocalDateTime;
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
public class FeedbackDto {

   // private Long feedbackid;

  
   // private Long userId;

	private String name;
   
    private String feedbackText;

 
    private String subject;
    

    private String email;
	
}
