package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import po.NormalUser;


@Repository
@Mapper
public interface NormalUserDao {
	public NormalUser getByUserNameAndPwd(@Param("userName") String userName,@Param("password") String password);
	public NormalUser getByUserName(@Param("userName") String userName);
	public Integer add(NormalUser user);
	public Integer update(NormalUser user);
	
	public Integer delete(@Param("userName") String userName);
	public List<NormalUser>  getall();
    
}
