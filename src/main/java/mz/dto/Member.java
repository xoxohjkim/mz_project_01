package mz.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	
	private String id;
	private String pwd;
	private String name;
	private String nickname;
	private String email;
	private int authKey;
	private int authState;
	private String useYn;
	
	@JsonFormat
	private Date regDate;
}
