package com.kolia.handlers;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import com.kolia.entities.User;
import com.kolia.hibernate.util.MyDBManager;
import com.kolia.services.CourseService;
import com.kolia.services.UserService;

import net.sf.json.JSONObject;

public class EditHandler extends Handler{
	UserService userService;
	CourseService courseService;
	public EditHandler(MyDBManager dbManager) {
		this.userService = new UserService();
		this.courseService = new CourseService();
		this.dbManager = dbManager;
	}
	
	MyDBManager dbManager = new MyDBManager();
	
//	@Override
//	public ResponseHandler doGet(HttpServletRequest request) {
//	
//		
//		
//	}
//	
	@Override
	public ResponseHandler doPost(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		String password  = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String email = request.getParameter("email");
		User user = userService.getUserByUserId(userId);
		userService.update(user);
		user.setUserId(userId);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setEmail(email);
		userService.update(user);
//		String password = request.getParameter("password");
//		String firstName = request.getParameter("firstName");
//		String lastName = request.getParameter("lastName");
		
		return new ResponseHandler();
		
	}
	
	
}
