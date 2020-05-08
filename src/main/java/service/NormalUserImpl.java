package service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.NormalUserDao;
import po.NormalUser;

@Service
@Transactional()
public  class NormalUserImpl implements NormalUserService{
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
    
	
	//用户管理模块
	@Override
	public void deleteNormalUser(String userName) {
		// TODO Auto-generated method stub
		normalUserDao.delete(userName);
	}

	@Override
	public List<NormalUser> getAllNormalUser() {
		// TODO Auto-generated method stub
		return normalUserDao.getall();
	}
    
	//用户个人
	public NormalUser getNormalUserByUid(long userId) {
		// TODO Auto-generated method stub
		System.out.println("impl");
		return normalUserDao.getByUid(userId);
	}
	

	
	
}
