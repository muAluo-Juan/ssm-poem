package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.AnswerDao;
import model.AnswerResult;
import utils.DynamicDataSourceHolder;

@Service
@Transactional
public class AnswerImpl implements AnswerService{
	@Autowired
	private AnswerDao answerDao;
	
	@Override
	public List<AnswerResult> getAllAnswersByQuestionId(int questionId) {
		// TODO Auto-generated method stub
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		return answerDao.getAllByQuestionId(questionId);
	}
	
}
