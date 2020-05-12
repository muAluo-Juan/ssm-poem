package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.CollectionDao;
import po.Collection;
import utils.DynamicDataSourceHolder;

@Service
@Transactional()
public class CollectionImpl implements CollectionService{

	@Autowired
	private CollectionDao collectionDao;
	
	@Override
	public List<Collection> getCollectionsAllByUserId(int userId) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		return collectionDao.getAllByUserId(userId);
	}

	@Override
	public Collection getCollectionById(int collectionId) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		return collectionDao.getById(collectionId);
	}

	//获取诗歌收藏数量
	@Override
	public Integer getNumberOfPoemCollection(long poemId) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		List<Collection> collections = collectionDao.getByPoemId(poemId);
		return collections.size();
	}

	@Override
	public void addCollection(Collection collection) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		collectionDao.add(collection);
	}

	@Override
	public void deleteCollection(int collectionId) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		collectionDao.delete(collectionId);
	}

	@Override
	public void deleteReferCollection(int userId, long poemId) {
		// TODO Auto-generated method stub
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		collectionDao.deleteRefer(userId,poemId);
	}

	@Override
	public Collection getByUserIdAndPoemId(int userId, long poemId) {
		// TODO Auto-generated method stub
		return collectionDao.getByUserIdAndPoemId(userId, poemId);
	}
	
}
