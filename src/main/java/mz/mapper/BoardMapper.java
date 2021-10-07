package mz.mapper;

import java.util.List;

import mz.dto.Board;
import mz.dto.BoardGroup;

public interface BoardMapper {
	List<Board> selectBoardByAll();
	List<Board> selectBoardByGroup(int bgrId);
	BoardGroup getBoardByGroupKey(String key);
	
	List<BoardGroup> selectBoardGroupByAll();
	List<BoardGroup> selectBoardGroupById(int brgId);
	Board selectBoardById(int id);
	
	int insertGnrBoard(Board board);
	int updateGnrBoard(int id);
	int deleteGnrBoard(int id);
}
