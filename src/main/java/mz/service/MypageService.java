package mz.service;

import java.util.List;

import mz.dto.Board;
import mz.dto.Comment;
import mz.dto.Criteria;

public interface MypageService {
	List<Board> pagingSelectMyBoardList(Criteria cri, String memId);
	int countSelectMyBoardList(Criteria cri, String memId);
	
	List<Comment> pagingSelectMyCmtList(Criteria cri, String memId);
	int countSelectMyCmtList(Criteria cri, String memId);
}
