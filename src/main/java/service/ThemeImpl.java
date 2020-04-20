package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.ThemeDao;
import po.Theme;

@Service
@Transactional()
public class ThemeImpl implements ThemeService{
	@Autowired
	private ThemeDao themeDao;
	
	@Override
	public void addTheme(Theme theme) {
		// TODO Auto-generated method stub
		themeDao.add(theme);
	}

	@Override
	public void deleteTheme(String themeNumber) {
		// TODO Auto-generated method stub
		themeDao.delete(themeNumber);
	}

	@Override
	public Theme getThemeByNumber(String themeNumber) {
		// TODO Auto-generated method stub
		return themeDao.getByNumber(themeNumber);
	}

	@Override
	public List<Theme> getAllThemes() {
		// TODO Auto-generated method stub
		return themeDao.getAll();
	}

	@Override
	public void update(Theme theme) {
		// TODO Auto-generated method stub
		themeDao.update(theme);
	}
	
}
