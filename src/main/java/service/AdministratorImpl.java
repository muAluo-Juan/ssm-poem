package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.AdministratorDao;
import po.Administrator;


@Service
@Transactional()
public class AdministratorImpl implements AdministratorService{

	@Autowired
	AdministratorDao adminDao;
	
	@Override
	public Administrator getAdministratorByUserNameAndPwd(String userName, String pwd) {
		return adminDao.getByUserNameAndPwd(userName, pwd);
	}

	@Override
	public Administrator getAdministratorByUserName(String userName) {
		// TODO Auto-generated method stub
		return adminDao.getByUserName(userName);
	}

	@Override
	public void modifyAdministratorInfo(Administrator admin) {
		// TODO Auto-generated method stub
		adminDao.update(admin);
	}
}
