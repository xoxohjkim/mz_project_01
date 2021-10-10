package mz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.dto.Comment;
import mz.mapper.CommentMapper;
import mz.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentMapper mapper;
	
	@Override
	public List<Comment> selectCmtListByBrdId(int brdId) {
		return mapper.selectCmtListByBrdId(brdId);	
	}

	@Override
	public int insertComment(Comment comment) {
		return mapper.insertComment(comment);
	}

	@Override
	public int updateComment(Comment comment) {
		return mapper.updateComment(comment);
	}

	@Override
	public int deleteComment(Comment comment) {
		return mapper.deleteComment(comment);
	}

	@Override
	public Comment getCommentById(int cmtId) {
		return mapper.getCommentById(cmtId);
	}

}
