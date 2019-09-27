package br.com.mvc.spring.thymeleaf.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public abstract class DAO <T, PK> {

	@SuppressWarnings("unchecked")
	private final Class<T> entityClass = 
			(Class<T>) ( (ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	
	@PersistenceContext
	private EntityManager manager;

	protected EntityManager getEntityManager() {
		return manager;
	}
	
	public void save(T entity) { 

		manager.persist(entity);
	}
	
	public void update(T entity) {
		
		manager.merge(entity);
	}
	
	public void delete(PK id) {
		
		manager.remove(manager.getReference(entityClass, id));
	}
	
	public T findById(PK id) {
		
		return manager.find(entityClass, id);
	}
	
	public List<T> findAll() {
		
		return manager
				.createQuery("from " + entityClass.getSimpleName(), entityClass)
				.getResultList();
	}	
	
	protected List<T> createQuery(String jpql, Object... params) {
		TypedQuery<T> query = manager.createQuery(jpql, entityClass);
		for (int i = 0; i < params.length; i++) {
		    query.setParameter(i+1, params[i]);
        }
    	return query.getResultList();
	}
}
