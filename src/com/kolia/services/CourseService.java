package com.kolia.services;

import java.util.List;

import org.hibernate.Session;

import com.kolia.entities.Course;
import com.kolia.hibernate.util.MyDBManager;

public class CourseService {

	MyDBManager dbManager = new MyDBManager();
	
	public CourseService() {
	}

	public CourseService(MyDBManager dbManager) {
		this.dbManager = dbManager;
	}

	public boolean registerCourse(Course course) {
		
		dbManager.startTransaction();
		if (isCourseExist(course)) {
			System.out.println("Course already exist");
			return false;
			
		}
		
		dbManager.persist(course);
		dbManager.closeTransaction();
		return true;
		
	}
	
	public boolean isCourseExist(Course course) {
		
		Session session = dbManager.getDBFactory().openSession();
		session.beginTransaction();
		boolean result = false;
		String sql = ("FROM Course WHERE name='" + course.getName() + "'");
		Course c = (Course) session.createQuery(sql).uniqueResult();
		if(c != null) {
			result = true;
		}
		
		session.getTransaction().commit();
		session.close();
		return result;
		
	}
	
	public List<Course> getAllCourses(){
		dbManager.startTransaction();
		List<Course> courses = dbManager.getResultList("FROM Course");
		dbManager.closeTransaction();
		return courses;
 	}
	
	public Course getCourseByName(String name) {
		dbManager.startTransaction();
		Course course;
		String sql = ("FROM Course WHERE name='" + name + "'");
		course = (Course) dbManager.getSingleResult(sql);	
		dbManager.closeTransaction();
		return course;
	}
	
	public void update(Course course) {
		dbManager.startTransaction();
		dbManager.saveOrUpdate(course);
		dbManager.closeTransaction();
	}
	
}
