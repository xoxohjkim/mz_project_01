package mz.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;
import mz.config.RootConfig;
import mz.dto.Board;
import mz.dto.BoardGroup;
import mz.mapper.BoardMapper;
import mz.mapper.MemberMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
@Log4j
public class MemberMapperTests {
	
	@Autowired
	private MemberMapper mapper;
	
	@Autowired
	private BoardMapper bMapper;
	
	@Test
	public void boardList() {
		List<Board> list = bMapper.selectBoardByAll();
		list.stream().forEach(System.out::println);
	}
	
	@Test
	public void brgList() {
		log.info("게시판 종류 list");
		List<BoardGroup> list = bMapper.selectBoardGroupByAll();
		list.stream().forEach(System.out::println);
	}
	
	@Test
	public void brgById() {
		log.info("게시판 종류 list by brg");
		List<Board> list = bMapper.selectBoardByGroup(1);
		list.stream().forEach(System.out::println);
	}
	
}
 