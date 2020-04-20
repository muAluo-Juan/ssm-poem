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
		DynamicDataSourceHolder.setDataSource("seconddataSource");
		return poemDao.getAll();
	}

	@Override
	public Poem getPoemByPoemId(long id) {
		DynamicDataSourceHolder.setDataSource("seconddataSource");
		return poemDao.getByPoemId(id);
	}

	@Override
	public List<Poem> getPoemsByName(String name) {
		DynamicDataSourceHolder.setDataSource("seconddataSource");
		return poemDao.getByName(name);
	}

	@Override
	public List<Poem> getPoemsByAuthorUId(String authoruid) {
		DynamicDataSourceHolder.setDataSource("seconddataSource");
		return poemDao.getByAuthorUId(authoruid);
	}

	@Override
	public List<Poem> getPoemsByTypeId(int typeid) {
		DynamicDataSourceHolder.setDataSource("seconddataSource");
		return poemDao.getByTypeId(typeid);
	}

	@Override
	public void updatePoem(Poem poem) {
		DynamicDataSourceHolder.setDataSource("seconddataSource");
		poemDao.update(poem);
	}

	@Override
	public void addPoem(Poem poem) {
		DynamicDataSourceHolder.setDataSource("seconddataSource");	
		poemDao.add(poem);
	}

	@Override
	public void deletePoem(long id) {
		DynamicDataSourceHolder.setDataSource("seconddataSource");
		poemDao.delete(id);
	}
	
}
