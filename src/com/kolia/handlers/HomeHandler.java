package com.kolia.handlers;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.kolia.entities.Course;
import com.kolia.entities.User;
import com.kolia.hibernate.util.MyDBManager;
import com.kolia.services.CourseService;
import com.kolia.services.UserService;

import net.sf.json.JSONObject;

public class HomeHandler extends Handler{
	UserService service;
	CourseService courseService = new CourseService();
	
	public HomeHandler(MyDBManager dbManager) {
		this.service = new UserService(dbManager);
		this.dbManager = dbManager;
	}

	MyDBManager dbManager = new MyDBManager();
	
	@Override
	public ResponseHandler doGet(HttpServletRequest request) {
		System.out.println("Getting all bookings (I'm in userHandler)");
		List<User> users = service.getAllUsers();
		List<Course> courses = courseService.getAllCourses();
		System.out.println("I'm in UserHandler, getting all users for You");
		JSONObject json = new JSONObject();
		json.put("users", users);
		json.put("courses", courses);
//		Thread t1 = new Thread(new UserService());
//		t1.start();
//		try {
//			System.out.println("Anew thread started");
//			t1.sleep(10000);
//			System.out.println("the thread woke aup from sleep after 10 s");
//		} catch (InterruptedException e) {
//			System.out.println("Thread interupted");
//			e.printStackTrace();
//		}
//		Thread t2 = new Thread(new UserService());
//		t2.start();
//		try {
//			System.out.println("T2 A new thread started");
//			t2.sleep(10000);
//			System.out.println("T2 the thread woke aup from sleep after 10 s");
//		} catch (InterruptedException e) {
//			System.out.println("T2 Thread interupted");
//			e.printStackTrace();
//		}
		service.getMappedUsers(users);
		
		return new JSONResponse(json);
		
	}
	
//	@Override
//	public ResponseHandler doPost(HttpServletRequest request) {
//		
//	}
	
}
