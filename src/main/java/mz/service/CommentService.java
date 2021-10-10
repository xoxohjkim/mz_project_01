package mz.service;

import java.util.List;

import org.springframework.stereotype.Service;

import mz.dto.Comment;

@Service
public interface CommentService {
	List<Comment> selectCmtListByBrdId(int brdId);
	Comment getCommentById(int cmtId);

	int insertComment(Comment comment);
	int updateComment(Comment comment);
	int deleteComment(Comment comment);
	
	int countByBrdId(int brdId);
}
