package service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import po.Author;

public interface AuthorService {
	//获取所有诗人
	public List<Author> getAllAuthors();
	//根据诗人id查找诗人
	public Author getAuthorByAuthorId(int id);
	//根据诗人名查找诗人
	public List<Author> getAuthorsByAuthorName(String name);
	//根据朝代id查找诗人
	public List<Author> getAuthorsByDynastyId(int dynastyId);
		
	//添加诗人
	public void addAuthor(Author author);
	//删除诗人
	public void deleteAuthor(int id);
	//更新诗人
	public void updateAuthor(Author author);
	//获取诗人根据uid
	public Author getAuthorByUid(String uid);
}
