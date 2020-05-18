package service;

import java.util.List;

import model.CommentResult;
import po.Comment;

public interface CommentService {
	public void deleteComment(int commentId);
	public void addComment(Comment comment);
	public List<CommentResult> getCommentByWorkId(int workId);
	public Comment getCommentByCommentId(int commentId);
}
