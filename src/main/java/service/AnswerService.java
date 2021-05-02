package service;

import java.util.List;

import model.AnswerResult;
import po.Answer;
import po.Attention;

public interface AnswerService {
	public List<AnswerResult> getAllAnswersByQuestionId(int questionId);
	public Integer addAnswer(Answer answer);
}
