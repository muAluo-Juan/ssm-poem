package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.LikeDao;
import po.Like;
import utils.DynamicDataSourceHolder;

@Service
@Transactional()
public class LikeImpl implements LikeService{
	
	@Autowired
	private LikeDao likeDao;
	
	@Override
	public void addLike(Like like) {
		// TODO Auto-generated method stub
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		likeDao.add(like);
	}

	@Override
	public void deleteLike(int userId, int beLikedId,int type) {
		// TODO Auto-generated method stub
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		likeDao.delete(userId,beLikedId, type);
	}

	@Override
	public Like getLike(int userId, int workId) {
		// TODO Auto-generated method stub
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		return likeDao.get(userId, workId);
	}

	@Override
	public List<Like> getWorkBeLiked(int userId) {
		// TODO Auto-generated method stub
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		return likeDao.getByUserId(userId);
	}

	@Override
	public Like getLikeByUserIdAndBeLikedId(int userId, int beLikedId, int type) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		return likeDao.getByUserIdAndBeLikedId(userId, beLikedId, type);
	}
}
