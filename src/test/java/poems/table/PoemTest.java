package poems.table;

import static org.junit.jupiter.api.Assertions.*;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PoemTest {

	private SqlSession session;
	
	@BeforeAll
	void init() {
		//加载mybatis环境
		try {
			String resource = "/mybatis/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterAll
	void destory() {
		if(session != null)
			session.close();
	}
	
	@Test
	void testGetAllPoems() {
		
	}

}
