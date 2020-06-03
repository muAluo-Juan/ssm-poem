package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.PointsGradeDao;
import po.PointsGrade;
import utils.DynamicDataSourceHolder;

@Service
@Transactional()
public class PointsGradeImpl implements PointsGradeService{

	@Autowired
	private PointsGradeDao pointsGradeDao;
	
	@Override
	public List<PointsGrade> getAllPointsGrades() {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		return pointsGradeDao.getAll();
	}

	@Override
	public List<PointsGrade> getPointsGradesByPoints(int miniPoints) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		return pointsGradeDao.getByPoints(miniPoints);
	}

	@Override
	public List<PointsGrade> getPointsGradesByGrade(String gradeName) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		return pointsGradeDao.getByGrade(gradeName);
	}

	@Override
	public PointsGrade getPointsGradeById(int gradeId) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		return pointsGradeDao.getById(gradeId);
	}

	@Override
	public void deletePointsGrade(int gradeId) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		pointsGradeDao.delete(gradeId);
	}

	@Override
	public void addPointsGrade(PointsGrade pointsGrade) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		pointsGradeDao.add(pointsGrade);
	}

	@Override
	public void updatePointsGrade(PointsGrade pointsGrade) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		pointsGradeDao.update(pointsGrade);
	}
	
}
