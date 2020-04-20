package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import po.ErrorInfo;

@Repository
@Mapper
public interface ErrorInfoDao {
	public ErrorInfo getById(@Param("errorId") int errorId);
	public List<ErrorInfo> getAll();
	public Integer delete(@Param("errorId") int errorId);
	public Integer add(ErrorInfo errorInfo);
	public Integer update(ErrorInfo errorInfo);
}
