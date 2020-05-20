package service;

import java.util.List;

import model.WorkResult;
import po.Work;

public interface WorkService {
	public void addWork(Work work);
	public void deleteWork(int workId);
	public List<WorkResult> getAllWorks();
	public List<WorkResult> getWorksByUserId(int userId);
	public Work getWorkByWrokId(int workId);
	public void updateWork(Work work);
}
