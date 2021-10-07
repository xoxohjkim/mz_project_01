package mz.service;

import java.util.List;

import mz.dto.Board;

public interface BoardService {
	List<Board> selectBoardByGroup(int bgrId);
	List<Board> selectBoardByAll();
}
