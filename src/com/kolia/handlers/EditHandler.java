package com.kolia.handlers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.kolia.entities.User;
import com.kolia.hibernate.util.MyDBManager;
import com.kolia.services.CourseService;
import com.kolia.services.UserService;

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
		
		String body = getBody(request);
		System.out.println(body);
		User u = new Gson().fromJson(body, User.class);
		
		
//		String password  = u.getPassword();
//		String firstName = u.getFirstName();
//		String email = u.getEmail();
//		String userId = u.getUserId();
		userService.update(u);
//		user.setUserId(userId);
//		user.setPassword(password);
//		user.setFirstName(firstName);
//		user.setEmail(email);
//		String password = request.getParameter("password");
//		String firstName = request.getParameter("firstName");
//		String lastName = request.getParameter("lastName");
		
		return new ResponseHandler();
		
	}
	
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
