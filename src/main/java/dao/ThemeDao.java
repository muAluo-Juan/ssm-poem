package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import po.Theme;

@Repository
@Mapper
public interface ThemeDao {
	public Integer add(Theme theme);
	public Integer delete(@Param("themeNumber") String themeNumber);
	public Theme getByNumber(@Param("themeNumber") String themeNumber);
	public List<Theme> getAll();
	public Integer update(Theme theme);
}
