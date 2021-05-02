package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import po.Like;

@Repository
@Mapper
public interface LikeDao {
	public Integer add(Like like);
	public Integer delete(@Param("userId") int userId,@Param("beLikedId") int beLikedId,@Param("type") int type);
	public Like get(@Param("userId") int userId,@Param("workId") int workId);
	public List<Like> getByUserId(@Param("userId") int userId);
	public Like getByUserIdAndBeLikedId(@Param("userId") int userId,@Param("beLikedId") int beLikedId, @Param("type") int type);
}
