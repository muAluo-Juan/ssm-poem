package service;

import java.util.List;

import po.Like;

public interface LikeService {
	public void addLike(Like like);
	public void deleteLike(int userId,int workId);
	public Like getLike(int userId,int workId);
	public List<Like> getLikesByUserId(int userId);
}
