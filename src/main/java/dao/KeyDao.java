package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import po.Key;

@Repository
@Mapper
public interface KeyDao {
	  public Integer addKey(Key key);
	  public Integer deleteKey(@Param("kId") long kId);
	  public Key getKeyById(@Param("kId")long kId);
	  public List<Key> getAllKeys();
}
