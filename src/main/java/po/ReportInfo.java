package po;

import java.sql.Date;

//举报信息
public class ReportInfo {
	private int reportId;
	private int userId;
	private int beReportedUserId;
	private String reportReason;
	private int state;
	private Date inputTime;
	public int getReportId() {
		return reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBeReportedUserId() {
		return beReportedUserId;
	}
	public void setBeReportedUserId(int beReportedUserId) {
		this.beReportedUserId = beReportedUserId;
	}
	public String getReportReason() {
		return reportReason;
	}
	public void setReportReason(String reportReason) {
		this.reportReason = reportReason;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getInputTime() {
		return inputTime;
	}
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	
}
