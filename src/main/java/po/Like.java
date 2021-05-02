package po;

import java.sql.Timestamp;

//点赞
public class Like {
	private long likeId;
	private int userId;
	private int beLikedId;
	private Timestamp likeTime;
	private int type;
	public void setLikeId(long likeId) {
		this.likeId = likeId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBeLikedId() {
		return beLikedId;
	}
	public void setBeLikedId(int beLikedId) {
		this.beLikedId = beLikedId;
	}
	public Timestamp getLikeTime() {
		return likeTime;
	}
	public void setLikeTime(Timestamp likeTime) {
		this.likeTime = likeTime;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getLikeId() {
		return likeId;
	}
}
