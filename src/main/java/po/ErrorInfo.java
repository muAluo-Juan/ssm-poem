package po;

import java.sql.Timestamp;

//诗歌勘误信息
public class ErrorInfo {
	private int errorId;
	private String errorText;//勘误内容
	private Timestamp inputTime;//勘误提交时间
	private int state;  //审核状态
	private int verifyAdministratorId;//审核勘误的管理员
	private int userId;//提交勘误的用户
	private long poemId;//存在勘误的诗歌id
	private Timestamp verifyTime;//勘误审核时间
	
	public Timestamp getVerifyTime() {
		return verifyTime;
	}
	public void setVerifyTime(Timestamp verifyTime) {
		this.verifyTime = verifyTime;
	}
	public int getErrorId() {
		return errorId;
	}
	public void setErrorId(int errorId) {
		this.errorId = errorId;
	}
	public String getErrorText() {
		return errorText;
	}
	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}
	
	public Timestamp getInputTime() {
		return inputTime;
	}
	public void setInputTime(Timestamp inputTime) {
		this.inputTime = inputTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getVerifyAdministratorId() {
		return verifyAdministratorId;
	}
	public void setVerifyAdministratorId(int verifyAdministratorId) {
		this.verifyAdministratorId = verifyAdministratorId;
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
	public void setPoemId(int poemId) {
		this.poemId = poemId;
	}
}
