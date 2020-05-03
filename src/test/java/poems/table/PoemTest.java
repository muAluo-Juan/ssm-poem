package poems.table;


import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

import dao.PoemDao;
import po.Poem;

public class PoemTest {
	
	@Test
	void testGetAllPoems() {
		SqlSession session = null;
		try {
			//加载mybatis配置环境
			String resource = "mybatis/mybatis-config.xml";
			InputStream inputStream=Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		List<Poem> poemList = session.getMapper(PoemDao.class).getAll();
		assertNotNull(poemList);
		
		if(session != null)
			session.close();
	}
}
