package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import po.PointsGrade;

@Repository
@Mapper
public interface PointsGradeDao {
	//获取所有积分等级
	public List<PointsGrade> getAll();
	//根据积分查找积分等级信息
	public List<PointsGrade> getByPoints(@Param("miniPoints") int miniPoints);
	//根据等级查找积分等级信息
	public List<PointsGrade> getByGrade(@Param("gradeName") String gradeName);
	//根据id获取某个积分等级
	public PointsGrade getById(@Param("gradeId") int gradeId);
	//删除积分等级
	public int delete(@Param("gradeId") int gradeId);
	//添加积分等级
	public int add(PointsGrade pointsGrade);
	//修改积分等级
	public int update(PointsGrade pointsGrade);
}
