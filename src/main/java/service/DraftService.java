package service;

import java.util.List;

import po.Draft;

public interface DraftService {
	public List<Draft> getAllDraftByUserId(int userId);
	public Draft getDraftByDraftId(int draftId);
	public void addDraft(Draft draft);
	public void deleteDraft(int draftId);
	public void updateDraft(Draft draft);
}
