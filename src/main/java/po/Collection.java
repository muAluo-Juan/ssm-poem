package po;

import java.sql.Timestamp;

//收藏
public class Collection {
	private int collectionId;
	private int userId;
	private long poemId;
	private Timestamp collectTime;
	
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
	public long getPoemId() {
		return poemId;
	}
	public void setPoemId(long poemId) {
		this.poemId = poemId;
	}
}
