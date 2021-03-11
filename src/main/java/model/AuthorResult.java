package model;

import java.util.List;

import po.Poem;

public class AuthorResult {
	private int id;
	private String uid;
	private String name;
	private String intro;
	private String avatar;
	private int dynastyid;
	private List<PoemResult> poems;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public int getDynastyid() {
		return dynastyid;
	}
	public void setDynastyid(int dynastyid) {
		this.dynastyid = dynastyid;
	}
	public List<PoemResult> getPoems() {
		return poems;
	}
	public void setPoems(List<PoemResult> poems) {
		this.poems = poems;
	}
	
}
