package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import model.AnswerResult;
import po.Answer;

@Repository
@Mapper
public interface AnswerDao {
	public List<AnswerResult> getAllByQuestionId(@Param("questionId") int questionId);
	public Integer add(Answer answer);
}
