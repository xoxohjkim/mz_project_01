package mz.mapper;

import java.util.List;

import mz.dto.Board;

public interface BoardMapper {
	List<Board> selectBoardByAll();
	List<Board> selectBoardByGroup(int bgrId);
}
