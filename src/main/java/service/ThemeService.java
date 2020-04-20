package service;

import java.util.List;

import po.Theme;

public interface ThemeService {
	public void addTheme(Theme theme);
	public void deleteTheme(String themeNumber);
	public Theme getThemeByNumber(String themeNumber);
	public List<Theme> getAllThemes();
	public void update(Theme theme);
}
