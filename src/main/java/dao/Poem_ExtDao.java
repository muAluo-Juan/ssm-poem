package dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import po.Poem_Ext;

@Repository
@Mapper
public interface Poem_ExtDao {
	//根据诗歌的uid获取诗歌的附加信息poem_ext
	public Poem_Ext getByPoemuid(@Param("poemuid") String poemuid);
	//更新poem_ext
	public Integer update(Poem_Ext poem_ext);
	//删除poem_ext
	public Integer delete(@Param("poemuid") String poemuid);
	//增加poem_ext
	public Integer add(Poem_Ext poem_ext);
}
