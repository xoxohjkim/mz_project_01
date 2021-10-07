package mz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;
import mz.dto.Board;
import mz.dto.BoardGroup;
import mz.dto.Criteria;
import mz.dto.SearchCriteria;
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
		return mapper.selectBoardByAll();
	}
	
	@Override
	public Board selectBoardById(int id) {
		return mapper.selectBoardById(id);
	}

	@Override
	public List<BoardGroup> getBoardGroupList() {
		return mapper.selectBoardGroupByAll();
	}

	@Override
	public List<BoardGroup> getBoardGroupListById(int bgrId) {
		return mapper.selectBoardGroupById(bgrId);
	}

	@Override
	public int insertGnrBoard(Board board) {
		return mapper.insertGnrBoard(board);
	}

	@Override
	public BoardGroup getBoardByGroupKey(String key) {
		return mapper.getBoardByGroupKey(key);
	}

	@Override
	public int modifyGnrBoard(Board board) {
		return mapper.updateGnrBoard(board);
	}

	@Override
	public int deleteGnrBoard(Board board) {
		return mapper.deleteGnrBoard(board);
	}

	@Override
	public List<Board> pagingSelectBoardByAll(SearchCriteria cri, String id, String cond, String keyword) {
		return mapper.pagingSelectBoardByAll(cri, id, cond, keyword);
	}

	@Override
	public int countSelectBoardByAll(SearchCriteria cri,String id, String cond, String keyword) {
		return mapper.countSelectBoardByAll(cri, id, cond, keyword);
	}

	

	
	
	

}
