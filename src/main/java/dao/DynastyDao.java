package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import po.Dynasty;

@Repository
@Mapper
public interface DynastyDao {
	public Integer add(Dynasty dynasty);
	public Integer delete(@Param("id") int id);
	public Dynasty getById(@Param("id") int id);
	public List<Dynasty> getAll();
	public Integer update(Dynasty dynasty);
}
