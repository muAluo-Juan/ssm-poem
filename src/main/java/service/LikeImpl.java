package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.LikeDao;
import po.Like;

@Service
@Transactional()
public class LikeImpl implements LikeService{
	
	@Autowired
	private LikeDao likeDao;
	
	@Override
	public void addLike(Like like) {
		// TODO Auto-generated method stub
		likeDao.add(like);
	}

	@Override
	public void deleteLike(int userId, int workId) {
		// TODO Auto-generated method stub
		likeDao.delete(userId, workId);
	}

	@Override
	public Like getLike(int userId, int workId) {
		// TODO Auto-generated method stub
		return likeDao.get(userId, workId);
	}
	
}
