package service;

import java.util.List;

import model.AnswerResult;

public interface AnswerService {
	public List<AnswerResult> getAllAnswersByQuestionId(int questionId);
}
