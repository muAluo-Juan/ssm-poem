package service;

import java.util.List;


import po.Collection;

public interface CollectionService {
	//获取用户所有收藏
	public List<Collection> getCollectionsAllByUserId(int userId);
	//查看某个收藏
	public Collection getCollectionById(int collectionId);
	//获取诗歌收藏数量
	public Integer getNumberOfPoemCollection(long poemId);
	//添加收藏
	public void addCollection(Collection collection);
	//删除收藏
	public void deleteCollection(int collectionId);
	//删除某个用户的某个收藏
	public void deleteReferCollection(int userId,long poemId);
	//获取某个用户的某个收藏
	public Collection getByUserIdAndPoemId(int userId,long poemId);
}
