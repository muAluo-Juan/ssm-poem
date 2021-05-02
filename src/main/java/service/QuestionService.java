package service;

import java.util.List;

import model.QuestionResult;
import po.Question;

public interface QuestionService {
	//获得问答列表
	public List<Question> getAllQuestions();
	//获得热门问答列表
	public List<Question> getAllHotQuestions();
	//获得未解决的问答列表
	public List<Question> getNoSolvedQuestions();
	//获得已解决的问答列表
	public List<Question> getSolvedQuestions();
	//根据id获取问题
	public Question getQuestionById(int questionId);
	//提问
	public Integer addQuestion(Question question);
	//采纳回答
	public void adoptAnswer(Question question);
	//搜索问题
	public List<Question> getSearchQuestions(String text);
} 
