package model;

import java.sql.Timestamp;

public class AnswerResult {
	private int answerId;
	private int questionId;
	private String text;
	private int likeNum;
	private int userId;
	private String penName;
	private String headPicPath;
	private Timestamp inputTime;
	private boolean likedByLoginer;
	private boolean showAdoptBtn;
	
	public boolean isShowAdoptBtn() {
		return showAdoptBtn;
	}
	public void setShowAdoptBtn(boolean showAdoptBtn) {
		this.showAdoptBtn = showAdoptBtn;
	}
	public boolean isLikedByLoginer() {
		return likedByLoginer;
	}
	public void setLikedByLoginer(boolean likedByLoginer) {
		this.likedByLoginer = likedByLoginer;
	}
	public int getAnswerId() {
		return answerId;
	}
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
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
	public int getLikeNum() {
		return likeNum;
	}
	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
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
	public Timestamp getInputTime() {
		return inputTime;
	}
	public void setInputTime(Timestamp inputTime) {
		this.inputTime = inputTime;
	}
}
