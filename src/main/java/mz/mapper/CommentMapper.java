package mz.mapper;

import java.util.List;

import mz.dto.Comment;

public interface CommentMapper {
	List<Comment> selectCmtListByBrdId(int brdId);
	Comment getCommentById(int cmtId);
	int insertComment(Comment comment);
	int updateComment(Comment comment);
	int deleteComment(Comment comment);
	int countByBrdId(int brdId);
}
