package service;

import po.Administrator;

public interface AdministratorService {
	public Administrator getAdministratorByUserNameAndPwd(String userName,String pwd);
	public Administrator getAdministratorByUserName(String userName);
	public void modifyAdministratorInfo(Administrator admin);
}
