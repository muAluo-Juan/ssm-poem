package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import po.Collection;

@Repository
@Mapper
public interface CollectionDao {
	//获取用户所有收藏
	public List<Collection> getAllByUserId(@Param("userId") int userId);
	//查看某个收藏
	public Collection getById(@Param("collectionId") int collectionId);
	//查询诗歌的收藏
	public List<Collection> getByPoemId(@Param("poemId") long poemId);
	//添加收藏
	public Integer add(Collection collection);
	//删除收藏
	public Integer delete(@Param("collectionId") int collectionId);
	//删除某个用户的某个收藏
	public Integer deleteRefer(@Param("userId") int userId,@Param("poemId") long poemId);
}
