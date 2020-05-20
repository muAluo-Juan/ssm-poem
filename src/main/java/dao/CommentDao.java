package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import model.CommentResult;
import po.Comment;

@Repository
@Mapper
public interface CommentDao {
	public Integer delete(@Param("commentId") int commentId);
	public Integer add(Comment comment);
	public List<CommentResult> getByWorkId(@Param("workId") int workId);
	public Comment getByCommentId(@Param("commentId") int commentId);
	public List<CommentResult> getAll();
}
