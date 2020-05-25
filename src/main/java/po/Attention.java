package po;

import java.sql.Timestamp;

//关注
public class Attention {
	private int attentionId;
	private int userId;
	private int beAttentedId;
	private Timestamp attentedTime;
	
	public Timestamp getAttentedTime() {
		return attentedTime;
	}
	public void setAttentedTime(Timestamp attentedTime) {
		this.attentedTime = attentedTime;
	}
	public int getAttentionId() {
		return attentionId;
	}
	public void setAttentionId(int attentionId) {
		this.attentionId = attentionId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBeAttentedId() {
		return beAttentedId;
	}
	public void setBeAttentedId(int beAttentedId) {
		this.beAttentedId = beAttentedId;
	}
}
