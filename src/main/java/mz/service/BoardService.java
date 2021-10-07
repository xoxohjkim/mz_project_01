package mz.service;

import java.util.List;

import mz.dto.Board;
import mz.dto.BoardGroup;
import mz.dto.Criteria;

public interface BoardService {
	List<Board> selectBoardByGroup(int bgrId);
	List<Board> selectBoardByAll();
	BoardGroup getBoardByGroupKey(String key);
	
	List<Board> pagingSelectBoardByAll(Criteria cri);
	int countSelectBoardByAll();
	
	List<BoardGroup> getBoardGroupList();
	List<BoardGroup> getBoardGroupListById(int bgrId);
	Board selectBoardById(int id);
	
	int insertGnrBoard(Board board);
	int modifyGnrBoard(Board board);
	int deleteGnrBoard(Board board);
}
