package mz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mz.dto.Board;
import mz.dto.Comment;
import mz.dto.Criteria;

public interface MypageMapper {
	List<Board> pagingSelectMyBoardList(@Param("cri")Criteria cri, @Param("id")String memId);
	int countSelectMyBoardList(@Param("cri")Criteria cri, @Param("id")String memId);
	
	List<Comment> pagingSelectMyCmtList(@Param("cri")Criteria cri, @Param("id")String memId);
	int countSelectMyCmtList(@Param("cri")Criteria cri, @Param("id")String memId);
}
