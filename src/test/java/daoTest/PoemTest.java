package daoTest;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import dao.PoemDao;
import po.Poem;

public class PoemTest extends BaseTest{
	@Autowired
	private PoemDao poemDao;
	
	@Test
	void testGetAllPoems() {
		assertTimeout(ofSeconds(1),()->{
			List<Poem> poems = poemDao.getAll();
		});
	}
}
