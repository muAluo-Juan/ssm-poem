package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.ErrorInfoDao;
import po.ErrorInfo;

@Service
@Transactional()
public class ErrorInfoImpl implements ErrorInfoService{

	@Autowired
	private ErrorInfoDao errorInfoDao;
	
	@Override
	public ErrorInfo getErrorInfoById(int errorId) {
		// TODO Auto-generated method stub
		return errorInfoDao.getById(errorId);
	}

	@Override
	public List<ErrorInfo> getAllErrorInfos() {
		// TODO Auto-generated method stub
		return errorInfoDao.getAll();
	}

	@Override
	public void deleteErrorInfo(int errorId) {
		// TODO Auto-generated method stub
		errorInfoDao.delete(errorId);
	}

	@Override
	public void addErrorInfo(ErrorInfo errorInfo) {
		// TODO Auto-generated method stub
		errorInfoDao.add(errorInfo);
	}

	@Override
	public void updateErrorInfo(ErrorInfo errorInfo) {
		// TODO Auto-generated method stub
		errorInfoDao.update(errorInfo);
	}

}
