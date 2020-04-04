package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.DraftDao;
import po.Draft;

@Service
@Transactional()
public class DraftImpl implements DraftService{

	@Autowired
	private DraftDao draftDao;
	
	@Override
	public List<Draft> getAllDraftByUserId(int userId) {
		// TODO Auto-generated method stub
		return draftDao.getAll(userId);
	}

	@Override
	public Draft getDraftByDraftId(int draftId) {
		// TODO Auto-generated method stub
		return draftDao.getById(draftId);
	}

	@Override
	public void addDraft(Draft draft) {
		draftDao.add(draft);
	}

	@Override
	public void deleteDraft(int draftId) {
		// TODO Auto-generated method stub
		draftDao.delete(draftId);
	}

	@Override
	public void updateDraft(Draft draft) {
		// TODO Auto-generated method stub
		draftDao.update(draft);
	}
	
}
