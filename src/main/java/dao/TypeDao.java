package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import po.Type;

@Repository
@Mapper
public interface TypeDao {
	public Integer add(Type type);
	public Integer delete(@Param("id") int id);
	public Type getById(@Param("id") int id);
	public List<Type> getAll();
	public Integer update(Type type);
}
