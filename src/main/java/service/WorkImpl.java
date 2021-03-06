package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.WorkDao;
import model.WorkResult;
import po.Work;
import utils.DynamicDataSourceHolder;

@Service
@Transactional()
public class WorkImpl implements WorkService{
	@Autowired
	private WorkDao workDao;
	
	@Override
	public void addWork(Work work) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		workDao.add(work);
	}

	@Override
	public void deleteWork(int workId) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		workDao.delete(workId);
	}

	@Override
	public List<WorkResult> getAllWorks() {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		return workDao.getAll();
	}

	@Override
	public List<WorkResult> getWorksByUserId(int userId) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		return workDao.getByUserId(userId);
	}

	@Override
	public WorkResult getWorkByWrokId(long workId) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		return workDao.getByWorkId(workId);
	}

	@Override
	public void updateWork(Work work) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		// TODO Auto-generated method stub
		workDao.update(work);
	}

	@Override
	public List<WorkResult> getAllWorksOrderByTime() {
		// TODO Auto-generated method stub
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		return workDao.getAllOrderByTime();
	}

	@Override
	public List<WorkResult> getAllWorksOrderByLike() {
		// TODO Auto-generated method stub
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		return workDao.getAllOrderByLike();
	}
	
}
