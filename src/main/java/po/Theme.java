package po;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

//诗歌主题（未用！！！）
public class Theme {
	private int themeId;
	private String themeName;
	private String themeNumber;
	public int getThemeId() {
		return themeId;
	}
	public void setThemeId(int themeId) {
		this.themeId = themeId;
	}
	public String getThemeName() {
		return themeName;
	}
	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}
	public String getThemeNumber() {
		return themeNumber;
	}
	public void setThemeNumber(String themeNumber) {
		this.themeNumber = themeNumber;
	}
}
