package service;

import java.util.List;

import model.PoemResult;
import po.Poem;

public interface PoemService {
	//获取所有诗歌列表
	public List<PoemResult> getAllPoems();
	//根据Id获取诗歌
	public PoemResult getPoemByPoemId(long id);
	//根据诗歌名模糊查找诗歌
	public List<Poem> getPoemsByName(String name);
	//根据作者id查找诗歌
	public List<PoemResult> getPoemsByAuthorUId(String authoruid);
	//根据诗歌类型查找诗歌
	public List<Poem> getPoemsByTypeId(int typeid);
		
	//更新诗歌内容
	public void updatePoem(Poem poem);
	//添加诗歌
	public void addPoem(Poem poem);
	//删除诗歌
	public void deletePoem(long id);
}
