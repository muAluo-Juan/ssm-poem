package service;

import java.util.List;

import model.WorkResult;
import po.Work;

public interface WorkService {
	public void addWork(Work work);
	public void deleteWork(int workId);
	public List<WorkResult> getAllWorks();
	public List<WorkResult> getWorksByUserId(int userId);
	public WorkResult getWorkByWrokId(long l);
	public void updateWork(Work work);
	public List<WorkResult> getAllWorksOrderByTime();
	public List<WorkResult> getAllWorksOrderByLike();
}

