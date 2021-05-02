package model;

import java.sql.Timestamp;
import java.util.List;

import po.Answer;

public class QuestionResult {
	private int questionId;
	private String text;
	private String description;
	private int likeNum;
	private int attentionNum;
	private int answerNum;
	private Timestamp inputTime;
	private int isSolved;
	private int userId;
	private String penName;
	private String headPicPath;
	private boolean likedByLoginer;
	private boolean attendByLoginer;
	public boolean isLikedByLoginer() {
		return likedByLoginer;
	}
	public void setLikedByLoginer(boolean likedByLoginer) {
		this.likedByLoginer = likedByLoginer;
	}
	public boolean isAttendByLoginer() {
		return attendByLoginer;
	}
	public void setAttendByLoginer(boolean attendByLoginer) {
		this.attendByLoginer = attendByLoginer;
	}
	private List<AnswerResult> answers;
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getLikeNum() {
		return likeNum;
	}
	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}
	public int getAttentionNum() {
		return attentionNum;
	}
	public void setAttentionNum(int attentionNum) {
		this.attentionNum = attentionNum;
	}
	public int getAnswerNum() {
		return answerNum;
	}
	public void setAnswerNum(int answerNum) {
		this.answerNum = answerNum;
	}
	public Timestamp getInputTime() {
		return inputTime;
	}
	public void setInputTime(Timestamp inputTime) {
		this.inputTime = inputTime;
	}
	public int getIsSolved() {
		return isSolved;
	}
	public void setIsSolved(int isSolved) {
		this.isSolved = isSolved;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getPenName() {
		return penName;
	}
	public void setPenName(String penName) {
		this.penName = penName;
	}
	public String getHeadPicPath() {
		return headPicPath;
	}
	public void setHeadPicPath(String headPicPath) {
		this.headPicPath = headPicPath;
	}
	public List<AnswerResult> getAnswers() {
		return answers;
	}
	public void setAnswers(List<AnswerResult> answers) {
		this.answers = answers;
	}
}
