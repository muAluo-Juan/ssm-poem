package service;

import java.util.List;

import po.ReportInfo;

public interface ReportService {
	public void addReportInfo(ReportInfo reportInfo);
	public void updateReportInfo(ReportInfo reportInfo);
	public List<ReportInfo> getAllReportInfo();
	public ReportInfo getReportInfo(int reportId);
}
