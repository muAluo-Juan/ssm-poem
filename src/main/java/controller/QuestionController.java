package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import model.AnswerResult;
import model.QuestionResult;
import model.Result;
import model.WorkResult;
import po.NormalUser;
import po.Question;
import service.AnswerService;
import service.NormalUserService;
import service.QuestionService;

//问答模块
@RestController
public class QuestionController {
	@Autowired 
	private QuestionService questionService;
	@Autowired 
	private NormalUserService normalUserService;
	@Autowired 
	private AnswerService answerService;
	
	/*
	 * 查看问答列表
	 */
	@CrossOrigin
	@GetMapping("/questions")
	public Result doGetQuestions() {
		try {
			List<Question> questions = questionService.getAllQuestions();
			List<QuestionResult> questionList = new ArrayList<>();
			for(Question q:questions) {
				NormalUser user = normalUserService.getNormalUserByUid(q.getUserId());
				List<AnswerResult> answers = answerService.getAllAnswersByQuestionId(q.getQuestionId());
				
				QuestionResult qs = new QuestionResult();
				qs.setAnswerNum(q.getAnswerNum());
				qs.setAnswers(answers);
				qs.setAttentionNum(q.getAttentionNum());
				qs.setDescription(q.getDescription());
				qs.setHeadPicPath(user.getHeadPicPath());
				qs.setInputTime(q.getInputTime());
				qs.setIsSolved(q.getIsSolved());
				qs.setLikeNum(q.getLikeNum());
				qs.setPenName(user.getPenName());
				qs.setQuestionId(q.getQuestionId());
				qs.setText(q.getText());
				qs.setUserId(q.getUserId());
				
				questionList.add(qs);
			}
			return new Result(200,"问答列表",questionList,null);
		}catch(Exception e)
		{
			e.printStackTrace();
			return new Result(500,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 查看人气问答列表
	 */
	@CrossOrigin
	@GetMapping("/questions/hot")
	public Result doGetHotQuestions() {
		try {
			List<Question> questions = questionService.getAllHotQuestions();
			List<QuestionResult> questionList = new ArrayList<>();
			for(Question q:questions) {
				NormalUser user = normalUserService.getNormalUserByUid(q.getUserId());
				List<AnswerResult> answers = answerService.getAllAnswersByQuestionId(q.getQuestionId());
				
				QuestionResult qs = new QuestionResult();
				qs.setAnswerNum(q.getAnswerNum());
				qs.setAnswers(answers);
				qs.setAttentionNum(q.getAttentionNum());
				qs.setDescription(q.getDescription());
				qs.setHeadPicPath(user.getHeadPicPath());
				qs.setInputTime(q.getInputTime());
				qs.setIsSolved(q.getIsSolved());
				qs.setLikeNum(q.getLikeNum());
				qs.setPenName(user.getPenName());
				qs.setQuestionId(q.getQuestionId());
				qs.setText(q.getText());
				qs.setUserId(q.getUserId());
				
				questionList.add(qs);
			}
			return new Result(200,"人气问答列表",questionList,null);
		}catch(Exception e)
		{
			e.printStackTrace();
			return new Result(500,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 查看未解决问答列表
	 */
	@CrossOrigin
	@GetMapping("/questions/nosolved")
	public Result doGetNoSolvedQuestions() {
		try {
			List<Question> questions = questionService.getNoSolvedQuestions();
			List<QuestionResult> questionList = new ArrayList<>();
			for(Question q:questions) {
				NormalUser user = normalUserService.getNormalUserByUid(q.getUserId());
				List<AnswerResult> answers = answerService.getAllAnswersByQuestionId(q.getQuestionId());
				
				QuestionResult qs = new QuestionResult();
				qs.setAnswerNum(q.getAnswerNum());
				qs.setAnswers(answers);
				qs.setAttentionNum(q.getAttentionNum());
				qs.setDescription(q.getDescription());
				qs.setHeadPicPath(user.getHeadPicPath());
				qs.setInputTime(q.getInputTime());
				qs.setIsSolved(q.getIsSolved());
				qs.setLikeNum(q.getLikeNum());
				qs.setPenName(user.getPenName());
				qs.setQuestionId(q.getQuestionId());
				qs.setText(q.getText());
				qs.setUserId(q.getUserId());
				
				questionList.add(qs);
			}
			return new Result(200,"未解决问答列表",questionList,null);
		}catch(Exception e)
		{
			e.printStackTrace();
			return new Result(500,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 查看已解决问答列表
	 */
	@CrossOrigin
	@GetMapping("/questions/solved")
	public Result doGetSolvedQuestions() {
		try {
			List<Question> questions = questionService.getSolvedQuestions();
			List<QuestionResult> questionList = new ArrayList<>();
			for(Question q:questions) {
				NormalUser user = normalUserService.getNormalUserByUid(q.getUserId());
				List<AnswerResult> answers = answerService.getAllAnswersByQuestionId(q.getQuestionId());
				
				QuestionResult qs = new QuestionResult();
				qs.setAnswerNum(q.getAnswerNum());
				qs.setAnswers(answers);
				qs.setAttentionNum(q.getAttentionNum());
				qs.setDescription(q.getDescription());
				qs.setHeadPicPath(user.getHeadPicPath());
				qs.setInputTime(q.getInputTime());
				qs.setIsSolved(q.getIsSolved());
				qs.setLikeNum(q.getLikeNum());
				qs.setPenName(user.getPenName());
				qs.setQuestionId(q.getQuestionId());
				qs.setText(q.getText());
				qs.setUserId(q.getUserId());
				
				questionList.add(qs);
			}
			return new Result(200,"已解决问答列表",questionList,null);
		}catch(Exception e)
		{
			e.printStackTrace();
			return new Result(500,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 根据id获取问题
	 */
	@CrossOrigin
	@GetMapping("/question/{questionId}")
	public Result doGetQuestion(@PathVariable("questionId") int questionId) {
		try {
			Question q = questionService.getQuestionById(questionId);
			QuestionResult qs = new QuestionResult();
			
			NormalUser user = normalUserService.getNormalUserByUid(q.getUserId());
			List<AnswerResult> answers = answerService.getAllAnswersByQuestionId(q.getQuestionId());

			qs.setAnswerNum(q.getAnswerNum());
			qs.setAnswers(answers);
			qs.setAttentionNum(q.getAttentionNum());
			qs.setDescription(q.getDescription());
			qs.setHeadPicPath(user.getHeadPicPath());
			qs.setInputTime(q.getInputTime());
			qs.setIsSolved(q.getIsSolved());
			qs.setLikeNum(q.getLikeNum());
			qs.setPenName(user.getPenName());
			qs.setQuestionId(q.getQuestionId());
			qs.setText(q.getText());
			qs.setUserId(q.getUserId());

			return new Result(200,"问答",qs,null);
		}catch(Exception e)
		{
			e.printStackTrace();
			return new Result(500,"出现未知错误",null,null);
		}
	}
}
