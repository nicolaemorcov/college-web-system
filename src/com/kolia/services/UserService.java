package com.kolia.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.kolia.entities.User;
import com.kolia.hibernate.util.MyDBManager;

public class UserService {
	
	MyDBManager dbManager = new MyDBManager();
	
	public boolean registerUser(User user) {
//		Session session = dbManager.getDBFactory().openSession();
//		session.beginTransaction();
		dbManager.startTransaction();
		
		if(isUserExists(user)) {
			return false;
		}
		
//		session.persist(user);
		dbManager.persist(user);
		
//		session.getTransaction().commit();
//		session.close();
		dbManager.closeTransaction();
		return true;
		
	}
	
	public boolean isUserExists(User user) {
//		Put these two lines in a method called 'starTransaction()';
//		Session session = dbManager.getDBFactory().openSession();
//		session.beginTransaction();
		dbManager.startTransaction();
		
		boolean result = false;
		String sql = ("FROM User WHERE userId='" + user.getUserId() + "'");
		// add session.createQuery() in a method called getSingleResult(String sql)
//		User u = (User) session.createQuery(sql).uniqueResult();
		User u = (User) dbManager.getSingleResult(sql);
		if (u != null) {
			result = true;
		}
		
//		Put these two lines in a method called 'closeTransaction()';
//		session.getTransaction().commit();
//		session.close();
		dbManager.closeTransaction();
		return result;
	}
	
	public User getUserByUserId(String userId) {
//		Session session = dbManager.getDBFactory().openSession();
//		session.beginTransaction();
		dbManager.startTransaction();
		
		User user;
		String sql = ("FROM User WHERE userId='" + userId + "'");
		user = (User) dbManager.getSingleResult(sql);
		
		dbManager.closeTransaction();
		return user;
		}
	
	public List<User> getAllUsers(){
		dbManager.startTransaction();
		List<User> users = dbManager.getResultList("FROM User");
//		try {
//		session.getTransaction().commit();
//		}finally {
//		session.close();
//		}
		dbManager.closeTransaction();
		return users;
	}
	
	public boolean authenticateUser(String userId, String password) {
		User user = getUserByUserId(userId);
		if (user != null && user.getUserId().equals(userId) && user.getPassword().equals(password)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void logout(String userId, String password) {
		Session session = dbManager.getDBFactory().openSession();
		session.beginTransaction();
		
		session.remove(userId);
		session.remove(password);
		
	}

}
