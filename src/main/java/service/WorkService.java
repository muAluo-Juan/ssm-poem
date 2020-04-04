package service;

import java.util.List;

import po.Work;

public interface WorkService {
	public void addWork(Work work);
	public void deleteWork(int workId);
	public List<Work> getAllWorks();
	public List<Work> getWorkByUserId(int userId);
	public Work getWorkByWrokId(int workId);
	public void updateWork(Work work);
}
