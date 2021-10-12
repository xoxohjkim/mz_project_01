package mz.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Mail {
	 
	 private String mailFrom;
	 
	    private String mailTo;
	    private String mailCc;
	    private String mailBcc;
	    private String mailSubject;
	    private String mailContent;
	    private String contentType;
	 
    
}