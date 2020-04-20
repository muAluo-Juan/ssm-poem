package service;

import java.util.List;

import po.Poem_Type;

public interface Poem_TypeService {
	public List<Poem_Type> getPoem_ThemeByPoemId(long poemId);//根据诗歌id查找诗歌的主题标签
	public List<Poem_Type> getPoem_ThemeByThemeId(long themeId);//根据主题id查找该主题的诗歌
	public void addPoem_Type(Poem_Type poem_type);//建立诗歌和主题间的联系
	public void deletePoem_Type(long ptId);//删除诗歌和主题之间的联系
	public Poem_Type getByPtId(long ptId);//根据ptId获取关系
	public void addManyPoem_Type(List<Poem_Type> poem_types);//批量插入
	public void deleteAllPoem_Type(long poemId);//删除对应poemId的poem_theme
}
