package mz.service;

import java.util.List;

import org.springframework.stereotype.Service;

import mz.dto.Board;
import mz.dto.BoardGroup;
import mz.dto.SearchCriteria;

@Service
public interface BoardService {
	List<Board> selectBoardByGroup(int bgrId);
	List<Board> selectBoardByAll();
	BoardGroup getBoardByGroupKey(String key);
	
	List<Board> pagingSelectBoardByAll(SearchCriteria cri,String id, String cond, String keyword);
	int countSelectBoardByAll(SearchCriteria cri, String id, String cond, String keyword);
	
	List<BoardGroup> getBoardGroupList();
	List<BoardGroup> getBoardGroupListById(int bgrId);
	Board selectBoardById(int id);
	
	int insertGnrBoard(Board board);
	int modifyGnrBoard(Board board);
	int deleteGnrBoard(Board board);


	
}
