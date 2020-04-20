package po;

//诗歌_主题关系
public class Poem_Type {
	private long ptId;
	private long poemId;
	private int themeId;
	public long getPtId() {
		return ptId;
	}
	public void setPtId(long ptId) {
		this.ptId = ptId;
	}
	public long getPoemId() {
		return poemId;
	}
	public void setPoemId(long poemId) {
		this.poemId = poemId;
	}
	public int getThemeId() {
		return themeId;
	}
	public void setThemeId(int themeId) {
		this.themeId = themeId;
	}
}
