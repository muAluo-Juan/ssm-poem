package service;


import po.Poem_Ext;

public interface Poem_ExtService {
	//根据诗歌的uid获取诗歌的附加信息poem_ext
	public Poem_Ext getPoem_ExtByPoemuid(String poemuid);
	//更新poem_ext
	public void updatePoem_Ext(Poem_Ext poem_ext);
	//删除poem_ext
	public void deletePoem_Ext(String poemuid);
	//增加poem_ext
	public void addPoem_Ext(Poem_Ext poem_ext);
}
