package service;

import java.util.List;


import po.ErrorInfo;

public interface ErrorInfoService {
	public ErrorInfo getErrorInfoById(int errorId);
	public List<ErrorInfo> getAllErrorInfos();
	public void deleteErrorInfo(int errorId);
	public void addErrorInfo(ErrorInfo errorInfo);
	public void updateErrorInfo(ErrorInfo errorInfo);
}
