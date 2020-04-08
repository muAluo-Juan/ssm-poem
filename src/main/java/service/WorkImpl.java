package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.WorkDao;
import po.Work;

@Service
@Transactional()
public class WorkImpl implements WorkService{
	@Autowired
	private WorkDao workDao;
	
	@Override
	public void addWork(Work work) {
		// TODO Auto-generated method stub
		workDao.add(work);
	}

	@Override
	public void deleteWork(int workId) {
		// TODO Auto-generated method stub
		workDao.delete(workId);
	}

	@Override
	public List<Work> getAllWorks() {
		return workDao.getAll();
	}

	@Override
	public List<Work> getWorksByUserId(int userId) {
		return workDao.getByUserId(userId);
	}

	@Override
	public Work getWorkByWrokId(int workId) {
		return workDao.getByWorkId(workId);
	}

	@Override
	public void updateWork(Work work) {
		// TODO Auto-generated method stub
		workDao.update(work);
	}
	
}
