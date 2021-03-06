package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import po.Attention;

@Repository
@Mapper
public interface AttentionDao {
	public Integer add(Attention attention);
	public Integer delete(@Param("userId") int userId,@Param("beAttentedId") int beAttentedId,@Param("type") int type);
	public List<Attention> getByUserId(@Param("userId") int userId);//获取关注人
	public List<Attention> getByBeAttentedId(@Param("beAttentedId") int beAttentedId);//获取粉丝
	public Attention getByUserIdAndAttentedId(@Param("beAttentedId") int beAttentedId,@Param("userId") int userId, @Param("type") int type);
}
