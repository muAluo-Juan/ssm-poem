package service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import po.Like;

public interface LikeService {
	public void addLike(Like like);
	public void deleteLike(int userId,int beLikedId,int type);
	public Like getLike(int userId,int workId);
	public List<Like> getWorkBeLiked(int userId);
	public Like getLikeByUserIdAndBeLikedId(int userId,int beLikedId,int type);
}
