package service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.NormalUserDao;
import po.NormalUser;

@Service
@Transactional()
public class NormalUserImpl implements NormalUserService{
	@Autowired
	private NormalUserDao normalUserDao;

	@Override
	public NormalUser getNormalUserByUserNameAndPwd(String userName, String pwd) {
		return normalUserDao.getByUserNameAndPwd(userName, pwd);
	}

	@Override
	public NormalUser getNormalUserByUserName(String userName) {
		// TODO Auto-generated method stub
		return normalUserDao.getByUserName(userName);
	}

	@Override
	public void addNormalUser(NormalUser user) {
		normalUserDao.add(user);
	}

	@Override
	public void modifyNormalUserInfo(NormalUser user) {
		// TODO Auto-generated method stub
		normalUserDao.update(user);
	}
}
