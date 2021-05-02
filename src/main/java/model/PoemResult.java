package model;

public class PoemResult {
	public long id;
	private String uid;
	private String dynasty;
	private String name;
	private String author;
	private String content;
	private String annotation;
	private String translation;
	private String audio;//音频
	private int dynastyid;
	private int typeid;
	private String authoruid;
	private boolean collect;
	public boolean isCollect() {
		return collect;
	}
	public void setCollect(boolean collect) {
		this.collect = collect;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getDynasty() {
		return dynasty;
	}
	public void setDynasty(String dynasty) {
		this.dynasty = dynasty;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAnnotation() {
		return annotation;
	}
	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
	public String getTranslation() {
		return translation;
	}
	public void setTranslation(String translation) {
		this.translation = translation;
	}
	public String getAudio() {
		return audio;
	}
	public void setAudio(String audio) {
		this.audio = audio;
	}
	public int getDynastyid() {
		return dynastyid;
	}
	public void setDynastyid(int dynastyid) {
		this.dynastyid = dynastyid;
	}
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public String getAuthoruid() {
		return authoruid;
	}
	public void setAuthoruid(String authoruid) {
		this.authoruid = authoruid;
	}
}
