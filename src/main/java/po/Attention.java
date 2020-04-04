package po;

//关注
public class Attention {
	private int attentionId;
	private int userId;
	private int beAttentedId;
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
