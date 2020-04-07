package po;


import java.sql.Date;

public class NormalUser {

	private int userId;
	
	private String userName;
	private String penName;
	private String password;
	private String headPicPath;
	private String personalizedSig;
	private int sex;
	private Date birth;
	private int disableTime;
	private long rewardPoints;//用户积分
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public int getDisableTime() {
		return disableTime;
	}
	public void setDisableTime(int disableTime) {
		this.disableTime = disableTime;
	}
	public long getRewardPoints() {
		return rewardPoints;
	}
	public void setRewardPoints(long rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
	
	public String get() {
		return this.userId+" "+this.userName + " "+this.penName+" "+this.password + this.personalizedSig+
				" "+this.headPicPath+" "+this.rewardPoints+this.sex+" "+this.disableTime + this.birth;
	}
}
