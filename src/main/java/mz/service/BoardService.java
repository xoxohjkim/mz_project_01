package mz.service;

import java.util.List;

import mz.dto.Board;
import mz.dto.BoardGroup;

public interface BoardService {
	List<Board> selectBoardByGroup(int bgrId);
	List<Board> selectBoardByAll();
	BoardGroup getBoardByGroupKey(String key);
	
	List<BoardGroup> getBoardGroupList();
	List<BoardGroup> getBoardGroupListById(int bgrId);
	Board selectBoardById(int id);
	
	int insertGnrBoard(Board board);
	int modifyGnrBoard(int id);
	int deleteGnrBoard(int id);
}
