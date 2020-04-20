package model;

import java.util.List;

/*提交、返回的诗歌信息*/
public class PoemForm {
	private String name;
	private int dynastyid;
	private List<Integer> typeids;
	private String authoruid;
	private String content;
	private String annotation;
	private String translation;
	private String workintro;
	private String richtext;
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
	
	public PoemForm() {
		super();
	}

	public PoemForm(String name, int dynastyid, List<Integer> typeids, String authoruid, String content,
			String annotation, String translation, String workintro, String richtext) {
		super();
		this.name = name;
		this.dynastyid = dynastyid;
		this.typeids = typeids;
		this.authoruid = authoruid;
		this.content = content;
		this.annotation = annotation;
		this.translation = translation;
		this.workintro = workintro;
		this.richtext = richtext;
	}

	public PoemForm(String name, int dynastyid, List<Integer> typeids, String authoruid, String content,
			String annotation, String translation, String workintro, String richtext, String engContent,
			String engIntro, String audio, String vedio, String yiwen_audio, String pic, String appreciation,
			String background, String version, String analyse, String story) {
		super();
		this.name = name;
		this.dynastyid = dynastyid;
		this.typeids = typeids;
		this.authoruid = authoruid;
		this.content = content;
		this.annotation = annotation;
		this.translation = translation;
		this.workintro = workintro;
		this.richtext = richtext;
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
	public List<Integer> getTypeids() {
		return typeids;
	}
	public void setTypeids(List<Integer> typeids) {
		this.typeids = typeids;
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
}
