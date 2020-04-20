package po;

//对应老师给的数据库中poem2的t_poems_poem
public class Poem {
	public long id;
	private String uid;
	private String name;
	private int dynastyid;
	private int typeid;
	private String authoruid;
	private String content;
	private String annotation;
	private String translation;
	private String workintro;
	private String richtext;
	private String memo;
	public Poem() {
		super();
	}
	public Poem(String name, int dynastyid,String authoruid, String content, String annotation,
			String translation, String workintro, String richtext) {
		super();
		this.name = name;
		this.dynastyid = dynastyid;
		this.authoruid = authoruid;
		this.content = content;
		this.annotation = annotation;
		this.translation = translation;
		this.workintro = workintro;
		this.richtext = richtext;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getWorkintro() {
		return workintro;
	}
	public void setWorkintro(String workintro) {
		this.workintro = workintro;
	}
	public String getRichtext() {
		return richtext;
	}
	public void setRichtext(String richtext) {
		this.richtext = richtext;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
}
