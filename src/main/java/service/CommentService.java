package service;

import java.util.List;

import po.Comment;

public interface CommentService {
	public void deleteComment(int commentId);
	public void addComment(Comment comment);
	public List<Comment> getCommentByWorkId(int workId);
	public Comment getCommentByCommentId(int commentId);
}
