package com.kolia.hibernate.util;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kolia.entities.User;

public class MyDBManager {
	Configuration cfg;
	
	SessionFactory sessionFactory;
	
	Session session;
	
	public void readAndCreateConfigFile() {
		
		cfg = new Configuration().configure();
	}
	
	
	
	public MyDBManager() {
		initJPA();
	}



	public void initJPA() {
		readAndCreateConfigFile();
		createDBFactory();
	}
	
	public void createDBFactory() {
		sessionFactory = cfg.buildSessionFactory();
	}
	
	public SessionFactory getDBFactory() {
		return sessionFactory;
	}
	
	public void startTransaction() {
		session = getDBFactory().openSession();
		session.beginTransaction();
	}
	
	public void closeTransaction() {
		session.getTransaction().commit();
		session.close();
	}
	
	public <E> Object getSingleResult(String sql) {
		return session.createQuery(sql).uniqueResult();
	}
	
	public <E> List<E> getResultList(String sql){
		return session.createQuery(sql).list();
	}
	
	public void persist(Object arg) {
		session.persist(arg);;
	}
	
	public <E extends Object> TypedQuery<E> createQuery(String sql, Class<E> entityClass){
		return session.createQuery(sql, entityClass);
	}
	
	public void saveOrUpdate(Object arg) {
		session.saveOrUpdate(arg);
	}
	
	public void delete(Object arg) {
		session.delete(arg);;
	}
	
//	public Session getSession() {
//		return session;
//	}

	
}
