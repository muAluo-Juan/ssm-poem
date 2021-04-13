package po;

import java.sql.Timestamp;

public class Work {
	private int workId;
	private String title;
	private int userId;
	private String text;
	private int likeNum;
	private Timestamp inputTime;//删除
	private int commentNum;
	private int collectNum;
	private Timestamp modifyTime;
	private int isDraft;
	private int isRecover;//删除
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
	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
}
