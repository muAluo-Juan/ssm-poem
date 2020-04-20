package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.CollectionDao;
import po.Collection;

@Service
@Transactional()
public class CollectionImpl implements CollectionService{

	@Autowired
	private CollectionDao collectionDao;
	
	@Override
	public List<Collection> getCollectionsAllByUserId(int userId) {
		// TODO Auto-generated method stub
		return collectionDao.getAllByUserId(userId);
	}

	@Override
	public Collection getCollectionById(int collectionId) {
		// TODO Auto-generated method stub
		return collectionDao.getById(collectionId);
	}

	//获取诗歌收藏数量
	@Override
	public Integer getNumberOfPoemCollection(long poemId) {
		// TODO Auto-generated method stub
		List<Collection> collections = collectionDao.getByPoemId(poemId);
		return collections.size();
	}

	@Override
	public void addCollection(Collection collection) {
		// TODO Auto-generated method stub
		collectionDao.add(collection);
	}

	@Override
	public void deleteCollection(int collectionId) {
		// TODO Auto-generated method stub
		collectionDao.delete(collectionId);
	}
	
}
