package com.appspot.geigerapi.data;

import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.sun.jersey.api.NotFoundException;

public abstract class Dao<T> {	
	private Class<T> clazz;
	
	protected Dao(Class<T> clazz) {
		this.clazz = clazz;
	}

	public T get(Long id){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		return get(pm, id);
	}

	protected T get(PersistenceManager pm, Long id) {
		T entity = null;
		try{
			entity = (T) pm.getObjectById(this.clazz, id);
		}catch(JDOObjectNotFoundException e){
		}
		if(entity == null) throw new NotFoundException("ID=" + id + " not found");
		return entity;
	}

	public List<T> getAll(String order) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(this.clazz);
		if (order != null) query.setOrdering(order);
		
		@SuppressWarnings("unchecked")
		List<T> entities = (List<T>)query.execute();
		return entities;
	}

	public void save(T entity) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		save(pm, entity);
	}

	protected void save(PersistenceManager pm, T entity) {
		try{
			pm.makePersistent(entity);
		}finally{
			pm.close();
		}
	}

	public T delete(Long id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		T entity = this.get(pm,id);
		return delete(pm, entity);
	}

	protected T delete(PersistenceManager pm, T entity) {
		try{
			pm.deletePersistent(entity);
		}finally{
			pm.close();
		}
		return entity;
	}

}
