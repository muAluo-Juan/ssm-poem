package po;

import java.sql.Timestamp;

//点赞
public class Like {
	private long likeId;
	private int userId;
	private int workId;
	private Timestamp inputTime;
	public long getLikeId() {
		return likeId;
	}
	public void setLikeId(long likeId) {
		this.likeId = likeId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getWorkId() {
		return workId;
	}
	public void setWorkId(int workId) {
		this.workId = workId;
	}
	public Timestamp getInputTime() {
		return inputTime;
	}
	public void setInputTime(Timestamp inputTime) {
		this.inputTime = inputTime;
	}
}
