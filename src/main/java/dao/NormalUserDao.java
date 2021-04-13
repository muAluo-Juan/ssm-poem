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
	
  
	
	
	/*
	 * 管理用户时需要添加的
	 */	
	public Integer delete(@Param("userName") String userName);
	public List<NormalUser>  getall();
	/*
	 * 用户个人模块
	 */
	public NormalUser getByUid(@Param("userId") int userId);
}
