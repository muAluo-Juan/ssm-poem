package controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import annotation.NormalToken;
import model.AdoptForm;
import model.AnswerResult;
import model.QuestionResult;
import model.Result;
import model.WorkResult;
import po.Answer;
import po.Attention;
import po.Collection;
import po.Comment;
import po.Like;
import po.NormalUser;
import po.Question;
import service.AnswerService;
import service.AttentionService;
import service.LikeService;
import service.NormalUserService;
import service.QuestionService;
import utils.JWTUtil;

//问答模块
@RestController
public class QuestionController {
	@Autowired 
	private QuestionService questionService;
	@Autowired 
	private NormalUserService normalUserService;
	@Autowired 
	private AnswerService answerService;
	@Autowired
	private LikeService likeService;
	@Autowired
	private AttentionService attentionService;
	
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
	public Result doGetQuestion(@PathVariable("questionId") int questionId, HttpServletRequest request) {
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
			
			//判断问题是否被已登录用户关注和点赞，首先判断用户是否登录
			String token = request.getHeader("token");
			if(token.equals("undefined")) {
				qs.setLikedByLoginer(false);
				qs.setAttendByLoginer(false);
				for(AnswerResult an: answers) {
					an.setLikedByLoginer(false);
					an.setShowAdoptBtn(false);
				}
			}else {
				String userName = JWTUtil.getUsername(token);
				NormalUser loginUser = normalUserService.getNormalUserByUserName(userName);
				System.out.println("得到的"+loginUser.getUserId());
				if(likeService.getLikeByUserIdAndBeLikedId(loginUser.getUserId(), questionId, 1)!=null)
					qs.setLikedByLoginer(true);
				else
					qs.setAttendByLoginer(false);
				if(attentionService.getAttentionByUserIdAndAttentedId(questionId, loginUser.getUserId(), 1)!=null)
					qs.setAttendByLoginer(true);
				else
					qs.setAttendByLoginer(false);
				for(AnswerResult an: answers) {
					Like al = likeService.getLikeByUserIdAndBeLikedId(loginUser.getUserId(), an.getAnswerId(), 2);//是否点赞回答
					if(al == null)
						an.setLikedByLoginer(false);
					else
						an.setLikedByLoginer(true);
				}
				if(loginUser.getUserId() == q.getUserId() && q.getIsSolved() == 0) //用户发布的问题并且还未被解决，显示采纳按钮
				{
					for(AnswerResult an: answers) 
						an.setShowAdoptBtn(true);
				}else {
					for(AnswerResult an: answers) 
						an.setShowAdoptBtn(false);
				}
			}
			
			return new Result(200,"问答",qs,null);
		}catch(Exception e)
		{
			e.printStackTrace();
			return new Result(500,"出现未知错误",null,null);
		}
	}
	
	/*
	 * 点赞/取消点赞
	 */
	@CrossOrigin
	@NormalToken
	@PostMapping(value="/like/question/{questionId}")
	public Result addLike(@PathVariable("questionId") int questionId,HttpServletRequest request) {
		try {
			String token = request.getHeader("token");
			String userName = JWTUtil.getUsername(token);
			NormalUser user = normalUserService.getNormalUserByUserName(userName);
			Like lk = likeService.getLikeByUserIdAndBeLikedId(user.getUserId(), questionId, 1);
			if(lk != null) {//取消点赞
				likeService.deleteLike(user.getUserId(), questionId, 1);
			}else {//点赞
				Like like = new Like();
				like.setUserId(user.getUserId());
				like.setBeLikedId(questionId);
				like.setType(1);
				Timestamp d = new Timestamp(System.currentTimeMillis());
				like.setLikeTime(d);
				likeService.addLike(like);
			}
			return new Result(200,"点赞成功",null,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(201,"发生未知错误",null,"");
		}
	}
	
	/*
	 * 关注/取消问题
	 */
	@CrossOrigin
	@NormalToken
	@PostMapping(value="/attention/question/{questionId}")
	public Result addAttention(@PathVariable("questionId") int questionId,HttpServletRequest request) {
		try {
			String token = request.getHeader("token");
			String userName = JWTUtil.getUsername(token);
			NormalUser user = normalUserService.getNormalUserByUserName(userName);
			if(attentionService.getAttentionByUserIdAndAttentedId(questionId, user.getUserId(), 1) == null) {//关注
				Attention attention = new Attention();
				Timestamp s = new Timestamp(System.currentTimeMillis());
				attention.setAttentionTime(s);
				attention.setBeAttentedId(questionId);
				attention.setType(1);
				attention.setUserId(user.getUserId());
				attentionService.addAttention(attention);
			}else {//取消关注
				attentionService.deleteAttention(user.getUserId(), questionId, 1);
			}
			return new Result(200,"操作成功",null,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(201,"发生未知错误",null,"");
		}
	}
	
	/*
	 * 发表回答
	 */
	@CrossOrigin
	@NormalToken
	@PostMapping(value="/answer")
	public Result comment(@RequestBody Answer answer,HttpServletRequest request) {
		try {
			String token = request.getHeader("token");
			String userName = JWTUtil.getUsername(token);
			NormalUser user = normalUserService.getNormalUserByUserName(userName);
			answer.setUserId(user.getUserId());
			Timestamp s = new Timestamp(System.currentTimeMillis());
			answer.setInputTime(s);
			answerService.addAnswer(answer);
			return new Result(200,"回答成功",null,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(201,"发生未知错误",null,"");
		}
	}
	
	/*
	 * 赞同/取消赞同回答
	 */
	@CrossOrigin
	@NormalToken
	@PostMapping(value="/like/answer/{answerId}")
	public Result addAnswerLike(@PathVariable("answerId") int answerId,HttpServletRequest request) {
		try {
			String token = request.getHeader("token");
			String userName = JWTUtil.getUsername(token);
			NormalUser user = normalUserService.getNormalUserByUserName(userName);
			Like lk = likeService.getLikeByUserIdAndBeLikedId(user.getUserId(), answerId, 2);
			if(lk != null) {//取消点赞
				likeService.deleteLike(user.getUserId(), answerId, 2);
			}else {//点赞
				Like like = new Like();
				like.setUserId(user.getUserId());
				like.setBeLikedId(answerId);
				like.setType(2);
				Timestamp d = new Timestamp(System.currentTimeMillis());
				like.setLikeTime(d);
				likeService.addLike(like);
			}
			return new Result(200,"赞同成功",null,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(201,"发生未知错误",null,"");
		}
	}
	
	/*
	 * 提问
	 */
	@CrossOrigin
	@NormalToken
	@PostMapping(value="/questions/addition")
	public Result addQuestion(@RequestBody Question question,HttpServletRequest request) {
		try {
			String token = request.getHeader("token");
			String userName = JWTUtil.getUsername(token);
			NormalUser user = normalUserService.getNormalUserByUserName(userName);//点赞
			question.setUserId(user.getUserId());
			Timestamp d = new Timestamp(System.currentTimeMillis());
			question.setInputTime(d);
			questionService.addQuestion(question);
			//获取所有问题，获得最大的id即为当前新增的id
			
			return new Result(200,"提问成功",null,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(201,"发生未知错误",null,"");
		}
	}
	
	/*
	 * 搜索问题
	 */
	@CrossOrigin
	@GetMapping("/questions/search/{text}")
	public Result doGetSearchQuestions(@PathVariable("text") String text) {//模糊匹配问题text
		try {
			List<Question> questions = questionService.getSearchQuestions(text);
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
			return new Result(200,"搜索列表",questionList,null);
		}catch(Exception e)
		{
			e.printStackTrace();
			return new Result(500,"出现未知错误",null,null);
		}
	}
	
	
	/*
	 * 采纳回答
	 */
	@CrossOrigin
	@NormalToken
	@PutMapping(value="/questions/modification")
	public Result adoptAnswer(@RequestBody AdoptForm adoptForm,HttpServletRequest request) {
		try {
			Question q = questionService.getQuestionById(adoptForm.getQuestionId());
			String token = request.getHeader("token");
			String userName = JWTUtil.getUsername(token);
			NormalUser user = normalUserService.getNormalUserByUserName(userName);
			if(user.getUserId() != q.getUserId())
				return new Result(202,"问题与提问用户不一致",null,"");
			q.setIsSolved(adoptForm.getAnswerId());
			questionService.adoptAnswer(q);
			return new Result(200,"采纳成功",null,null);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result(201,"发生未知错误",null,"");
		}
	}
	
}
