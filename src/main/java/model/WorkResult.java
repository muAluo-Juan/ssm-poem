package model;

import java.sql.Timestamp;

public class WorkResult {
	private int workId;
	private String title;
	private int userId;
	private String text;
	private String imgPath;
	private int likeNum;
	private int isEssay;
	private Timestamp inputTime;
	private String userName;
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
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public int getLikeNum() {
		return likeNum;
	}
	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}
	public int getIsEssay() {
		return isEssay;
	}
	public void setIsEssay(int isEssay) {
		this.isEssay = isEssay;
	}
	public Timestamp getInputTime() {
		return inputTime;
	}
	public void setInputTime(Timestamp inputTime) {
		this.inputTime = inputTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
}
