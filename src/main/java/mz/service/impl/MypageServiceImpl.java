package mz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.dto.Board;
import mz.dto.Comment;
import mz.dto.Criteria;
import mz.mapper.MypageMapper;
import mz.service.MypageService;

@Service
public class MypageServiceImpl implements MypageService {

	@Autowired
	private MypageMapper mapper;
	
	@Override
	public List<Board> pagingSelectMyBoardList(Criteria cri, String memId) {
		return mapper.pagingSelectMyBoardList(cri, memId);
	}

	@Override
	public int countSelectMyBoardList(Criteria cri, String memId) {
		return mapper.countSelectMyBoardList(cri, memId);
	}

	@Override
	public List<Comment> pagingSelectMyCmtList(Criteria cri, String memId) {
		return mapper.pagingSelectMyCmtList(cri, memId);
	}

	@Override
	public int countSelectMyCmtList(Criteria cri, String memId) {
		return mapper.countSelectMyCmtList(cri, memId);
	}

}
