package dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import po.NormalUser;


@Repository
@Mapper
public interface NormalUserDao {
	public NormalUser getByUserNameAndPwd(@Param("userName") String userName,@Param("password") String password);
	public NormalUser getByUserName(String userName);
	public Integer add(NormalUser user);
	public Integer update(NormalUser user);
}
