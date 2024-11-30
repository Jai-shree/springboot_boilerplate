package com.sample.springboot_boilerplate.dto;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;






import java.sql.Timestamp;
import java.util.Date;




@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDTO {
   private String reporteeEmail;
   private Date meetingDate;
   private String mode;
   private String feedbackEntered;
}
