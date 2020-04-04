package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import po.ReportInfo;

@Repository
@Mapper
public interface ReportDao {
	public Integer add(ReportInfo reportInfo);
	public Integer update(ReportInfo reportInfo);
	public List<ReportInfo> getAll();
	public ReportInfo getByReportId(@Param("reportId") int reportId);
} 
