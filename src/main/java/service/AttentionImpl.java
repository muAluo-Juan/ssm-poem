package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.AttentionDao;
import po.Attention;

@Service
@Transactional()
public class AttentionImpl implements AttentionService{

	@Autowired
	private AttentionDao attentionDao;
	

	@Override
	public List<Attention> getAttentions(int userId) {
		// TODO Auto-generated method stub
		return attentionDao.getByUserId(userId);
	}

	@Override
	public List<Attention> getFans(int beAttentedId) {
		// TODO Auto-generated method stub
		return attentionDao.getByBeAttentedId(beAttentedId);
	}

	@Override
	public void addAttention(Attention attention) {
		// TODO Auto-generated method stub
		attentionDao.add(attention);
	}

	@Override
	public void deleteAttention(int userId, int beAttentedId,int type) {
		// TODO Auto-generated method stub
		attentionDao.delete(userId, beAttentedId,type);
	}

	@Override
	public Attention getAttentionByUserIdAndAttentedId(int beAttentedId, int userId,int type) {
		// TODO Auto-generated method stub
		return attentionDao.getByUserIdAndAttentedId(beAttentedId, userId, type);
	}
	
}
