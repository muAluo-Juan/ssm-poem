package service;

import java.util.List;

import po.Type;

public interface TypeService {
	public void addType(Type type);
	public void deleteType(int id);
	public Type getTypeById(int id);
	public List<Type> getAllTypes();
	public void update(Type type);
}
