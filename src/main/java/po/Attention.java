package po;

import java.sql.Timestamp;

//关注
public class Attention {
	private int attentionId;
	private int userId;
	private int beAttentedId;
	private Timestamp attentionTime;
	private int type;
	
	public Timestamp getAttentionTime() {
		return attentionTime;
	}
	public void setAttentionTime(Timestamp attentionTime) {
		this.attentionTime = attentionTime;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
