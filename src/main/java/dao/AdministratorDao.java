package dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import po.Administrator;


@Repository
@Mapper
public interface AdministratorDao {
	public Administrator getByUserNameAndPwd(String userName,String password);
	public Administrator getByUserName(String userName);
	public void update(Administrator admin);
}
