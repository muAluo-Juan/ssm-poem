package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import po.Poem_Type;

@Repository
@Mapper
public interface Poem_TypeDao {
	public List<Poem_Type> getByPoemId(@Param("poemId") long poemId);//根据诗歌id查找诗歌的主题标签
	public List<Poem_Type> getByThemeId(@Param("themeId") long themeId);//根据主题id查找该主题的诗歌
	public Integer add(Poem_Type poem_type);//建立诗歌和主题间的联系
	public Integer delete(@Param("ptId") long ptId);//删除诗歌和主题之间的联系
	public Poem_Type getByPtId(@Param("ptId") long ptId);//根据ptId获取关系
	public Integer deleteAll(@Param("poemId") long poemId);//删除该poemId对应的所有poem_theme
}
