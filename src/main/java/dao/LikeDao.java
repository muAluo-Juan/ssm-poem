package dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import po.Like;

@Repository
@Mapper
public interface LikeDao {
	public Integer add(Like like);
	public Integer delete(@Param("userId") int userId,@Param("workId") int workId);
	public Like get(@Param("userId") int userId,@Param("workId") int workId);
}