package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.CommentDao;
import po.Comment;

@Service
@Transactional()
public class CommentImpl implements CommentService{

	@Autowired
	private CommentDao commentDao;
	
	@Override
	public void deleteComment(int commentId) {
		// TODO Auto-generated method stub
		commentDao.delete(commentId);
	}

	@Override
	public void addComment(Comment comment) {
		// TODO Auto-generated method stub
		commentDao.add(comment);
	}

	@Override
	public List<Comment> getCommentByWorkId(int workId) {
		// TODO Auto-generated method stub
		return commentDao.getByWorkId(workId);
	}

	@Override
	public Comment getCommentByCommentId(int commentId) {
		// TODO Auto-generated method stub
		return commentDao.getByCommentId(commentId);
	}
	
}
