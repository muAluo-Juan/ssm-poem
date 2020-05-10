package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.Poem_ExtDao;
import po.Poem_Ext;
import utils.DynamicDataSourceHolder;

@Service
@Transactional()
public class Poem_ExtImpl implements Poem_ExtService{

	@Autowired
	private Poem_ExtDao poem_ExtDao;
	
	@Override
	public Poem_Ext getPoem_ExtByPoemuid(String poemuid) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		return poem_ExtDao.getByPoemuid(poemuid);
	}

	@Override
	public void updatePoem_Ext(Poem_Ext poem_ext) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		poem_ExtDao.update(poem_ext);
	}

	@Override
	public void deletePoem_Ext(String poemuid) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		poem_ExtDao.delete(poemuid);
	}

	@Override
	public void addPoem_Ext(Poem_Ext poem_ext) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		poem_ExtDao.add(poem_ext);
	}
	
}
