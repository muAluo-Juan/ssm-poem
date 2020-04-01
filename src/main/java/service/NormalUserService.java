package service;

import po.NormalUser;

public interface NormalUserService {
	public NormalUser getNormalUserByUserNameAndPwd(String userName,String pwd);
	public NormalUser getNormalUserByUserName(String userName);
	public void addNormalUser(NormalUser user);
	public void modifyNormalUserInfo(NormalUser user);
}
