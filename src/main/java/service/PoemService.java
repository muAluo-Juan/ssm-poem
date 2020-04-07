package service;

import java.util.List;

import po.Poem;

public interface PoemService {
	public void addPoem(Poem poem);
	public void deletePoem(int poemId);
	public List<Poem> getAllPoems();
	public List<Poem> getPoemByUserId(int userId);
	public Poem getPoemByWrokId(int poemId);
	public void updatePoem(Poem poem);
}
