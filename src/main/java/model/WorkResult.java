package model;

import java.sql.Timestamp;

public class WorkResult {
	private int workId;
	private String title;
	private int userId;
	private String text;
	private int likeNum;
	private int commentNum;
	private int collectNum;
	private Timestamp modifyTime;
	private String penName;
	private String headPicPath;
	private String personalizedSig;
	private String userName;
	private boolean isCollectByLoginer;
	private boolean isLikedByLoginer;
	private int attendAuthor; //作品的作者是否被用户关注
	
	
	public int getAttendAuthor() {
		return attendAuthor;
	}
	public void setAttendAuthor(int attendAuthor) {
		this.attendAuthor = attendAuthor;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
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
	public boolean isCollectByLoginer() {
		return isCollectByLoginer;
	}
	public void setCollectByLoginer(boolean isCollectByLoginer) {
		this.isCollectByLoginer = isCollectByLoginer;
	}
	public boolean isLikedByLoginer() {
		return isLikedByLoginer;
	}
	public void setLikedByLoginer(boolean isLikedByLoginer) {
		this.isLikedByLoginer = isLikedByLoginer;
	}
}
