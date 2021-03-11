package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import model.PoemResult;
import po.Poem;

@Repository
@Mapper
public interface PoemDao {
	//获取所有诗歌列表
	public List<PoemResult> getAll();
	//根据Id获取诗歌
	public PoemResult getByPoemId(@Param("id") long id);
	//根据诗歌名模糊查找诗歌
	public List<Poem> getByName(@Param("name") String name);
	//根据作者uid查找诗歌
	public List<PoemResult> getByAuthorUId(@Param("authoruid") String authoruid);
	//根据诗歌类型查找诗歌
	public List<Poem> getByTypeId(@Param("typeid") int typeid);
	
	//更新诗歌内容
	public Integer update(Poem poem);
	//添加诗歌
	public Long add(Poem poem);
	//删除诗歌
	public Integer delete(@Param("id") long id);
}
