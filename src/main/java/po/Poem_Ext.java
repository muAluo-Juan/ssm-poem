package po;

/*对应老师poem2的t_poems_poem_ext表*/
public class Poem_Ext {
	private long id;
	private String uid;
	private String engContent;
	private String engIntro;
	private String audio;
	private String vedio;
	private String yiwen_audio;
	private String pic;
	private String appreciation;
	private String background;
	private String version;
	private String analyse;
	private String story;
	private String memo;
	private String poemuid;
	public Poem_Ext() {
		super();
	}
	public Poem_Ext(String engContent, String engIntro, String audio, String vedio, String yiwen_audio, String pic,
			String appreciation, String background, String version, String analyse, String story) {
		super();
		this.engContent = engContent;
		this.engIntro = engIntro;
		this.audio = audio;
		this.vedio = vedio;
		this.yiwen_audio = yiwen_audio;
		this.pic = pic;
		this.appreciation = appreciation;
		this.background = background;
		this.version = version;
		this.analyse = analyse;
		this.story = story;
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
	public String getEngContent() {
		return engContent;
	}
	public void setEngContent(String engContent) {
		this.engContent = engContent;
	}
	public String getEngIntro() {
		return engIntro;
	}
	public void setEngIntro(String engIntro) {
		this.engIntro = engIntro;
	}
	public String getAudio() {
		return audio;
	}
	public void setAudio(String audio) {
		this.audio = audio;
	}
	public String getVedio() {
		return vedio;
	}
	public void setVedio(String vedio) {
		this.vedio = vedio;
	}
	public String getYiwen_audio() {
		return yiwen_audio;
	}
	public void setYiwen_audio(String yiwen_audio) {
		this.yiwen_audio = yiwen_audio;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getAppreciation() {
		return appreciation;
	}
	public void setAppreciation(String appreciation) {
		this.appreciation = appreciation;
	}
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.background = background;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getAnalyse() {
		return analyse;
	}
	public void setAnalyse(String analyse) {
		this.analyse = analyse;
	}
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getPoemuid() {
		return poemuid;
	}
	public void setPoemuid(String poemuid) {
		this.poemuid = poemuid;
	}
}
