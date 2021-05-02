package model;

import po.NormalUser;

public class UserResult {
	private NormalUser normaluser;
	private String grade;
	private int fans;
	private int workNum;
	public NormalUser getNormaluser() {
		return normaluser;
	}
	public void setNormaluser(NormalUser normaluser) {
		this.normaluser = normaluser;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getFans() {
		return fans;
	}
	public void setFans(int fans) {
		this.fans = fans;
	}
	public int getWorkNum() {
		return workNum;
	}
	public void setWorkNum(int workNum) {
		this.workNum = workNum;
	}
}
