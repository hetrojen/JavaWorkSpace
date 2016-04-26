package com.company.rating.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, ID extends Serializable> {
	  
	T find(ID id);
	
	List<T> findAll();
	
	void save(T entity);
	
	void saveAll(List<T> entity);
	
	void update(T entity);
	
	void delete(T entity);

}
