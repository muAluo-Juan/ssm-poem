package dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import model.MyUser;
import po.MyUserTable;

@Repository
@Mapper
public interface HandleDao {
	public int register(MyUser myUser);
	public List<MyUserTable> login(MyUser myUser);
	public List<Map<String, Object>> selectAllUserByPage(@Param("startIndex") int startIndex, @Param("perPageSize") int perPageSize);
	public List<Map<String, Object>> selectAll();
}
