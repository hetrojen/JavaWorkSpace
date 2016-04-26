package com.company.rating.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.company.rating.dao.GenericDao;


@Repository
public abstract class GenericDaoImpl<T, ID extends Serializable> implements GenericDao<T,ID> {

	 	private Class<T> persistentClass;
	 	
	 	protected EntityManager entityManager;

	 	@SuppressWarnings("unchecked")
	    public GenericDaoImpl() {
	        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	    }

	 	@PersistenceContext
		public void setEntityManager(EntityManager entityManager) {
			this.entityManager = entityManager;
		}	

	    public Class<T> getPersistentClass() {
	        return persistentClass;
	    }

	    public T find(ID id) {
	       return entityManager.find(persistentClass, id);
	    }

	    public void save(T entity) {
	        entityManager.persist(entity);
	    }
	     
	    public void saveAll(List<T> list) {
	    	for (T t : list) {
	    		entityManager.persist(t);
	    	}
	    	
	    }
	    
	    public void update(T entity) {
	        entityManager.merge(entity);
	    }
	    
	    public void delete(T entity) {
	        entityManager.remove(entity);
	    }
	    
	    @SuppressWarnings("unchecked")
		public List<T> findAll() {
	        return entityManager.createQuery("Select t from " + persistentClass.getSimpleName() + " t").getResultList();
	    }
}
