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
public class Category {
	private int id;
	private BoardGroup bgr;
	private String key;
	private String name;
	private int parentId;
	private int order;
	
}
