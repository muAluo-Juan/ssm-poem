package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.PoemDao;
import po.Poem;

@Service
public class PoemImpl implements PoemService {

	@Autowired
	private PoemDao poemDao;
	@Override
	public void addPoem(Poem poem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePoem(int poemId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Poem> getAllPoems() {
		// TODO Auto-generated method stub
		return poemDao.getAll();
	}

	@Override
	public List<Poem> getPoemByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Poem getPoemByWrokId(int poemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePoem(Poem poem) {
		// TODO Auto-generated method stub
		
	}

}
