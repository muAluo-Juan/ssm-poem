package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.PoemDao;
import dao.QuestionDao;
import model.QuestionResult;
import po.Question;

@Service
@Transactional()
public class QuestionImpl implements QuestionService{

	@Autowired
	private QuestionDao questionDao;
	
	@Override
	public List<Question> getAllQuestions() {
		// TODO Auto-generated method stub
		return questionDao.getAll();
	}

	@Override
	public List<Question> getAllHotQuestions() {
		// TODO Auto-generated method stub
		return questionDao.getAllHot();
	}

	@Override
	public List<Question> getNoSolvedQuestions() {
		// TODO Auto-generated method stub
		return questionDao.getAllNoSolved();
	}

	@Override
	public List<Question> getSolvedQuestions() {
		// TODO Auto-generated method stub
		return questionDao.getAllSolved();
	}

	@Override
	public Question getQuestionById(int questionId) {
		// TODO Auto-generated method stub
		return questionDao.getById(questionId);
	}

	@Override
	public Integer addQuestion(Question question) {
		// TODO Auto-generated method stub
		return questionDao.add(question);
	}

	@Override
	public void adoptAnswer(Question question) {
		// TODO Auto-generated method stub
		questionDao.update(question);
	}

	@Override
	public List<Question> getSearchQuestions(String text) {
		// TODO Auto-generated method stub
		return questionDao.getSearch(text);
	}
}
