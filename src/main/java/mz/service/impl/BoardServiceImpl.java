package mz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;
import mz.dto.Board;
import mz.mapper.BoardMapper;
import mz.service.BoardService;

@Service
@Log4j
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper mapper;
	
	@Override
	public List<Board> selectBoardByGroup(int bgrId) {
		// TODO Auto-generated method stub
		return mapper.selectBoardByGroup(bgrId);
	}

	@Override
	public List<Board> selectBoardByAll() {
		List<Board> list = mapper.selectBoardByAll();
		return list;
	}

}
