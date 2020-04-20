package po;

//对应老师数据库poem2表t_poems_poet
public class Author {
	private int id;
	private String uid;
	private String name;
	private String name_zi;
	private String name_hao;
	private int gender;
	private String birthday;
	private String deathday;
	private int dynastyid;
	private String intro;
	private String masterwork;
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getName_zi() {
		return name_zi;
	}
	public void setName_zi(String name_zi) {
		this.name_zi = name_zi;
	}
	public String getName_hao() {
		return name_hao;
	}
	public void setName_hao(String name_hao) {
		this.name_hao = name_hao;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getDeathday() {
		return deathday;
	}
	public void setDeathday(String deathday) {
		this.deathday = deathday;
	}
	public int getDynastyid() {
		return dynastyid;
	}
	public void setDynastyid(int dynastyid) {
		this.dynastyid = dynastyid;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getMasterwork() {
		return masterwork;
	}
	public void setMasterwork(String masterwork) {
		this.masterwork = masterwork;
	}
}
