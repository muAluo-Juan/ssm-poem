package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import po.Poem;
import utils.Page;

@Repository
@Mapper
public interface PoemDao {
	public Integer add(Poem poem);
	public Integer delete(int poemId);
	public List<Poem> getAll();
	public List<Poem> getByUserId(int userId);
	public Poem getByPoemId(int poemId);
	public Integer update(Poem poem);
}
