package mz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mz.dto.Board;
import mz.dto.BoardGroup;
import mz.dto.Criteria;

public interface BoardMapper {
	
	List<Board> pagingSelectBoardByAll(@Param("cri")Criteria cri);
	int countSelectBoardByAll();
	
	
	List<Board> selectBoardByAll();
	List<Board> selectBoardByGroup(int bgrId);
	BoardGroup getBoardByGroupKey(String key);
	
	List<BoardGroup> selectBoardGroupByAll();
	List<BoardGroup> selectBoardGroupById(int brgId);
	Board selectBoardById(int id);
	
	int insertGnrBoard(Board board);
	int updateGnrBoard(Board board);
	int deleteGnrBoard(Board Board);
}
