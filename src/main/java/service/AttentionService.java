package service;

import java.util.List;

import po.Attention;

public interface AttentionService {
	public void addAttention(Attention attention);
	public void deleteAttention(int attentionId);
	public List<Attention> getAttentions(int userId);
	public List<Attention> getFans(int beAttentedId);
}
