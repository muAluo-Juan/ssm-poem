package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.ReportDao;
import po.ReportInfo;

@Service
@Transactional()
public class ReportImpl implements ReportService{
	
	@Autowired
	private ReportDao reportDao;
	
	@Override
	public void addReportInfo(ReportInfo reportInfo) {
		reportDao.add(reportInfo);
	}

	@Override
	public void updateReportInfo(ReportInfo reportInfo) {
		reportDao.update(reportInfo);
	}

	@Override
	public List<ReportInfo> getAllReportInfo() {
		return reportDao.getAll();
	}

	@Override
	public ReportInfo getReportInfo(int reportId) {
		return reportDao.getByReportId(reportId);
	}
	
}
