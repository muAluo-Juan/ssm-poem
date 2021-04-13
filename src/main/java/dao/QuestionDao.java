package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import model.QuestionResult;
import po.Question;


@Repository
@Mapper
public interface QuestionDao {
	public List<Question> getAll();
	public List<Question> getAllHot();
	public List<Question> getAllNoSolved();
	public List<Question> getAllSolved();
	public Question getById(@Param("questionId") int questionId);
}
