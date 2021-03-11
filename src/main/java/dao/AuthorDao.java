package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import model.AuthorResult;
import po.Author;

@Repository
@Mapper
public interface AuthorDao {
	//获取所有诗人
	public List<Author> getAll();
	//根据诗人id查找诗人
	public Author getByAuthorId(@Param("id") int id);
	//根据诗人名查找诗人
	public List<Author> getByAuthorName(@Param("name") String name);
	//根据朝代id查找诗人
	public List<Author> getByDynastyId(@Param("dynastyid") int dynastyid);
	
	//添加诗人
	public Integer add(Author author);
	//删除诗人
	public Integer delete(@Param("id") int id);
	//更新诗人
	public Integer update(Author author);
	//根据uid获取诗人
	public Author getByUid(@Param("uid") String uid);
} 
