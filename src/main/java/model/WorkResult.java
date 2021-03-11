package model;

import java.sql.Timestamp;

public class WorkResult {
	private int workId;
	private String title;
	private int userId;
	private String text;
	private int likeNum;
	private Timestamp inputTime;
	private int commentNum;
	private int collectNum;
	private Timestamp modifyTime;
	private int isDraft;
	private int isRecover;
	private String penName;
	private String headPicPath;
	private String personalizedSig;
	public int getWorkId() {
		return workId;
	}
	public void setWorkId(int workId) {
		this.workId = workId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public Timestamp getInputTime() {
		return inputTime;
	}
	public void setInputTime(Timestamp inputTime) {
		this.inputTime = inputTime;
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
	public String getPersonalizedSig() {
		return personalizedSig;
	}
	public void setPersonalizedSig(String personalizedSig) {
		this.personalizedSig = personalizedSig;
	}
	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	public int getCollectNum() {
		return collectNum;
	}
	public void setCollectNum(int collectNum) {
		this.collectNum = collectNum;
	}
	public Timestamp getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}
	public int getIsDraft() {
		return isDraft;
	}
	public void setIsDraft(int isDraft) {
		this.isDraft = isDraft;
	}
	public int getIsRecover() {
		return isRecover;
	}
	public void setIsRecover(int isRecover) {
		this.isRecover = isRecover;
	}
}
