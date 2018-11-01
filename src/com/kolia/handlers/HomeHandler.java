package com.kolia.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public ResponseHandler doPost(HttpServletRequest request) {
		System.out.println("Getting all bookings (I'm in userHandler)");
//		String userId = getBody(request).toString().replaceAll("\"", "");
		JSONObject json = new JSONObject();;
//		User u = service.getUserByUserId(userId);
//		String role = u.getRole();
		ResponseHandler rh;
//		if(role.equalsIgnoreCase("LECTURER") || role.equalsIgnoreCase("ADMIN")) {
			List<User> users = service.getAllUsers();
			List<Course> courses = courseService.getAllCourses();
			System.out.println("I'm in UserHandler, getting all users for You");
			json.put("users", users);
			json.put("courses", courses);
	
			service.getMappedUsers(users);
			return new JSONResponse(json);
		}
//		else {
//			rh = new ResponseHandler();
//			rh.setStatusCode(302);
//			rh.setContentType("text/plain");
//			
//		}
//		return rh;
		
//	}
	
	private String getBody(HttpServletRequest req) {
		  String body = "";
		  if (req.getMethod().equals("POST") )
		  {
		    StringBuilder sb = new StringBuilder();
		    BufferedReader bufferedReader = null;

		    try {
		      bufferedReader =  req.getReader();
		      char[] charBuffer = new char[128];
		      int bytesRead;
		      while ((bytesRead = bufferedReader.read(charBuffer)) != -1) {
		        sb.append(charBuffer, 0, bytesRead);
		      }
		    } catch (IOException ex) {
		      // swallow silently -- can't get body, won't
		    } finally {
		      if (bufferedReader != null) {
		        try {
		          bufferedReader.close();
		        } catch (IOException ex) {
		          // swallow silently -- can't get body, won't
		        }
		      }
		    }
		    body = sb.toString();
		  }
		  return body;
		}
	
	
}

