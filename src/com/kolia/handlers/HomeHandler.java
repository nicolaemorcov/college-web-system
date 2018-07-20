package com.kolia.handlers;

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
		
		return new JSONResponse(json);
		
	}
	
//	@Override
//	public ResponseHandler doPost(HttpServletRequest request) {
//		
//	}
	
}
