package dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import po.Administrator;


@Repository
@Mapper
public interface AdministratorDao {
	public Administrator getByUserNameAndPwd(@Param("userName") String userName,@Param("password") String password);
	public Administrator getByUserName(@Param("userName") String userName);
	public void update(Administrator admin);
}
