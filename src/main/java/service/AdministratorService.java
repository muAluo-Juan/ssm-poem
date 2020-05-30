package service;

import org.apache.ibatis.annotations.Param;

import po.Administrator;

public interface AdministratorService {
	public Administrator getAdministratorByUserNameAndPwd(String userName,String pwd);
	public Administrator getAdministratorByUserName(String userName);
	public Administrator getAdministratorById(int administratorId);
	public void modifyAdministratorInfo(Administrator admin);
}
