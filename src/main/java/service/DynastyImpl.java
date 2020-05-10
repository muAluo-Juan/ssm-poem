package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.DynastyDao;
import po.Dynasty;
import utils.DynamicDataSourceHolder;

@Service
@Transactional()
public class DynastyImpl implements DynastyService{

	@Autowired
	private DynastyDao dynastyDao;
	
	@Override
	public void addDynasty(Dynasty dynasty) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		dynastyDao.add(dynasty);
	}

	@Override
	public void deleteDynasty(int id) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		dynastyDao.delete(id);
	}

	@Override
	public Dynasty getDynastyById(int id) {
		// TODO Auto-generated method stub
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		return dynastyDao.getById(id);
	}

	@Override
	public List<Dynasty> getAllDynastys() {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		return dynastyDao.getAll();
	}

	@Override
	public void updateDynasty(Dynasty dynasty) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		dynastyDao.update(dynasty);
	}
	
}
