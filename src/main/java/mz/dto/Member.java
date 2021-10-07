package mz.dto;

import java.sql.Date;

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
	private String authState;
	private String useYn;
	private Date regDate;
}
