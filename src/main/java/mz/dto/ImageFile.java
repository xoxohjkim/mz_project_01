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
public class ImageFile {
	
	private int id;
	private int brdId;
	private Member member;
	private String name;
	private Date regDate;
	
}

