package service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import po.Attention;

public interface AttentionService {
	public void addAttention(Attention attention);
	public void deleteAttention(int userId,int beAttentedId,int type);
	public List<Attention> getAttentions(int userId);
	public List<Attention> getFans(int beAttentedId);
	public Attention getAttentionByUserIdAndAttentedId(int beAttentedId,int userId,int type);
}
