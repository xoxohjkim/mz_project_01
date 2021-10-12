package mz.service.impl;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.pattern.FileLocationPatternConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;
import mz.dto.Board;
import mz.dto.BoardGroup;
import mz.dto.Comment;
import mz.dto.Criteria;
import mz.dto.ImageFile;
import mz.dto.Member;
import mz.dto.SearchCriteria;
import mz.mapper.BoardMapper;
import mz.mapper.CommentMapper;
import mz.service.BoardService;
import mz.service.CommentService;
import mz.service.ImgFileService;

@Service
@Log4j
public class BoardServiceImpl implements BoardService {
	
	private static final String UPLOAD_PATH = "resources" + File.separator + "upload" + File.separator + "img" ;


	@Autowired
	private BoardMapper mapper;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private ImgFileService fileService;
	
	@Override
	public List<Board> selectBoardByGroup(int bgrId) {
		// TODO Auto-generated method stub
		return mapper.selectBoardByGroup(bgrId);
	}

	@Override
	public List<Board> selectBoardByAll() {
		return mapper.selectBoardByAll();
	}
	
	@Override
	public Board selectBoardById(int id) {
		return mapper.selectBoardById(id);
	}

	@Override
	public List<BoardGroup> getBoardGroupList() {
		return mapper.selectBoardGroupByAll();
	}

	@Override
	public List<BoardGroup> getBoardGroupListById(int bgrId) {
		return mapper.selectBoardGroupById(bgrId);
	}

	@Override
	public int insertGnrBoard(Board board) {
		return mapper.insertGnrBoard(board);
	}

	@Override
	public BoardGroup getBoardByGroupKey(String key) {
		return mapper.getBoardByGroupKey(key);
	}

	@Override
	public int modifyGnrBoard(Board board) {
		return mapper.updateGnrBoard(board);
	}
	
	@Transactional
	@Override
	public int deleteGnrBoard(Member loginUser, Board board, File realPath) {
		int res = 0;
		List<ImageFile> fileList = fileService.selectFileByBrdId(board.getId());
		board.setFileList(fileList);
		
		System.out.println("realPath:" + realPath);
		if(fileList != null) {
			res += fileService.deleteFile(loginUser, board, realPath);
		}
		
		//일반 - 게시물 + 댓글
		res += mapper.deleteGnrBoard(board);
		
		List<Comment> cmtList = commentService.selectCmtListByBrdId(board.getId());
		for(Comment c : cmtList) {
			res += commentService.deleteComment(c);
		}		
		
		return res;
	}
	

	@Override
	public List<Board> pagingSelectBoardByAll(SearchCriteria cri, String id, String cond, String keyword) {
		return mapper.pagingSelectBoardByAll(cri, id, cond, keyword);
	}

	@Override
	public int countSelectBoardByAll(SearchCriteria cri,String id, String cond, String keyword) {
		return mapper.countSelectBoardByAll(cri, id, cond, keyword);
	}

	
	
	
	

}
