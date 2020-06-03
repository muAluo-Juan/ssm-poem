package service;

import java.util.List;

import po.PointsGrade;

public interface PointsGradeService {
		//获取所有积分等级
		public List<PointsGrade> getAllPointsGrades();
		//根据积分查找积分等级信息
		public List<PointsGrade> getPointsGradesByPoints(int miniPoints);
		//根据等级查找积分等级信息
		public List<PointsGrade> getPointsGradesByGrade(String gradeName);
		//根据id获取某个积分等级
		public PointsGrade getPointsGradeById(int gradeId);
		//删除积分等级
		public void deletePointsGrade(int gradeId);
		//添加积分等级
		public void addPointsGrade(PointsGrade pointsGrade);
		//修改积分等级
		public void updatePointsGrade(PointsGrade pointsGrade);
}
