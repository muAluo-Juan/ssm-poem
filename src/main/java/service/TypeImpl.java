package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.TypeDao;
import po.Type;
import utils.DynamicDataSourceHolder;

@Service
@Transactional()
public class TypeImpl implements TypeService{
	@Autowired
	private TypeDao typeDao;
	
	@Override
	public void addType(Type type) {
		DynamicDataSourceHolder.setDataSource("seconddataSource");
		typeDao.add(type);
	}

	@Override
	public void deleteType(int id) {
		DynamicDataSourceHolder.setDataSource("seconddataSource");
		typeDao.delete(id);
	}

	@Override
	public Type getTypeById(int id) {
		DynamicDataSourceHolder.setDataSource("seconddataSource");
		return typeDao.getById(id);
	}

	@Override
	public List<Type> getAllTypes() {
		DynamicDataSourceHolder.setDataSource("seconddataSource");
		return typeDao.getAll();
	}

	@Override
	public void update(Type type) {
		DynamicDataSourceHolder.setDataSource("seconddataSource");
		typeDao.update(type);
	}
	
}
