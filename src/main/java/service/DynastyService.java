package service;

import java.util.List;

import po.Dynasty;

public interface DynastyService {
	public void addDynasty(Dynasty dynasty);
	public void deleteDynasty(int id);
	public Dynasty getDynastyById(int id);
	public List<Dynasty> getAllDynastys();
	public void updateDynasty(Dynasty dynasty);
}
