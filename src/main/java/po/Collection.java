package po;

import java.sql.Timestamp;

//收藏
public class Collection {
	private int collectionId;
	private int userId;
	private long beCollectedId;
	private Timestamp collectTime;
	private int type;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Timestamp getCollectTime() {
		return collectTime;
	}
	public void setCollectTime(Timestamp collectTime) {
		this.collectTime = collectTime;
	}
	public int getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(int collectionId) {
		this.collectionId = collectionId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public long getBeCollectedId() {
		return beCollectedId;
	}
	public void setBeCollectedId(long beCollectedId) {
		this.beCollectedId = beCollectedId;
	}
}
