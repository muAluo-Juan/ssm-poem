package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.PoemDao;
import po.Poem;
import utils.DynamicDataSourceHolder;

@Service
@Transactional()
public class PoemImpl implements PoemService{

	@Autowired
	private PoemDao poemDao;

	@Override
	public List<Poem> getAllPoems() {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		return poemDao.getAll();
	}

	@Override
	public Poem getPoemByPoemId(long id) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		return poemDao.getByPoemId(id);
	}

	@Override
	public List<Poem> getPoemsByName(String name) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		return poemDao.getByName(name);
	}

	@Override
	public List<Poem> getPoemsByAuthorUId(String authoruid) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		return poemDao.getByAuthorUId(authoruid);
	}

	@Override
	public List<Poem> getPoemsByTypeId(int typeid) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		return poemDao.getByTypeId(typeid);
	}

	@Override
	public void updatePoem(Poem poem) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		poemDao.update(poem);
	}

	@Override
	public void addPoem(Poem poem) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		poemDao.add(poem);
	}

	@Override
	public void deletePoem(long id) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		poemDao.delete(id);
	}
	
}
