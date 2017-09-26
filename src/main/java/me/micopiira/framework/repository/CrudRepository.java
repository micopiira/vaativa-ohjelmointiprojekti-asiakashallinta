package me.micopiira.framework.repository;

import java.util.List;

public interface CrudRepository<T, ID> {
	List<T> findAll();
	void delete(T entity);
	void deleteById(ID id);
	T findOne(ID id);
	T save(T entity);
}
