package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import model.WorkResult;
import po.Work;

@Repository
@Mapper
public interface WorkDao {
	public Integer add(Work work);
	public Integer delete(@Param("workId") int workId);
	public List<WorkResult> getAll();
	public List<WorkResult> getByUserId(@Param("userId") int userId);
	public Work getByWorkId(@Param("workId") int workId);
	public Integer update(Work work);
}
