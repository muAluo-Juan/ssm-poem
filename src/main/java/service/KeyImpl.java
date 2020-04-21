package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.KeyDao;
import po.Key;

@Service
@Transactional()
public class KeyImpl implements KeyService {
    
	@Autowired
	private KeyDao keyDao; 
	
	@Override
	public void addKey(Key key) {
		keyDao.addKey(key);
	}

	@Override
	public void deleteKey(long kId) {
		keyDao.deleteKey(kId);
		
	}

	@Override
	public Key getKeyById(long kId) {
		// TODO Auto-generated method stub
		return keyDao.getKeyById(kId);
	}

	@Override
	public List<Key> getAllKeys() {
		// TODO Auto-generated method stub
		return keyDao.getAllKeys();
	}

}
