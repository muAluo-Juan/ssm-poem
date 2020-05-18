package daoTest;

import java.util.List;

import org.junit.Test;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import org.springframework.beans.factory.annotation.Autowired;

import dao.NormalUserDao;
import po.NormalUser;

public class NormalUserDaoTest extends BaseTest{
	//注入NormalUserDao
	@Autowired
	private NormalUserDao normalUserDao;
	
	@Test
	public void queryAll() {
		assertTimeout(ofSeconds(1),()->{
			List<NormalUser> users= normalUserDao.getall();
			System.out.println(users);
		});
	}
}
