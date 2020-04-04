package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import po.Draft;

@Repository
@Mapper
public interface DraftDao {
	public List<Draft> getAll(@Param("userId") int userId);
	public Draft getById(@Param("draftId") int draftId);
	public Integer add(Draft draft);
	public Integer delete(@Param("draftId") int draftId);
	public Integer update(Draft draft);
}
