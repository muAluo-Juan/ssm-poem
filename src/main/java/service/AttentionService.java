package service;

import java.util.List;

import po.Attention;

public interface AttentionService {
	public void addAttention(int userId,int beAttentedId);
	public void deleteAttention(int userId,int beAttentedId);
	public List<Attention> getAttentions(int userId);
	public List<Attention> getFans(int beAttentedId);
}
