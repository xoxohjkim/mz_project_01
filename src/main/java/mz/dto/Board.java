package mz.dto;

import java.sql.Date;
import java.util.List;

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
public class Board {
	
	private int id;
	private BoardGroup bgr;
	private Member member;
	private String title;
	private String content;
	private int hit;
	private Date regDate;
	private List<ImageFile> fileList;
	private int cmtCnt;

}
