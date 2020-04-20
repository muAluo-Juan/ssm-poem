package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.Poem_TypeDao;
import po.Poem_Type;
import utils.DynamicDataSourceHolder;

@Service
@Transactional()
public class Poem_TypeImpl implements Poem_TypeService{
	@Autowired
	private Poem_TypeDao poem_typeDao;

	@Override
	public List<Poem_Type> getPoem_ThemeByPoemId(long poemId) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		return poem_typeDao.getByPoemId(poemId);
	}

	@Override
	public List<Poem_Type> getPoem_ThemeByThemeId(long themeId) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		return poem_typeDao.getByThemeId(themeId);
	}

	@Override
	public void addPoem_Type(Poem_Type poem_type) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		poem_typeDao.add(poem_type);
	}

	@Override
	public void deletePoem_Type(long ptId) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		poem_typeDao.delete(ptId);
	}

	@Override
	public Poem_Type getByPtId(long ptId) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		return poem_typeDao.getByPtId(ptId);
	}

	//批量插入
	@Override
	public void addManyPoem_Type(List<Poem_Type> poem_types) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		for(int i = 0 ; i < poem_types.size() ; i ++) {
			poem_typeDao.add(poem_types.get(i));
		}
	}

	@Override
	public void deleteAllPoem_Type(long poemId) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		poem_typeDao.deleteAll(poemId);
	}
	
}
