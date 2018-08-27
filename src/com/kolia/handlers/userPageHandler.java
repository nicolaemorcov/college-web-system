package com.kolia.handlers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.kolia.entities.User;
import com.kolia.hibernate.util.MyDBManager;
import com.kolia.services.CourseService;
import com.kolia.services.UserService;

import jdk.nashorn.api.scripting.JSObject;
import net.sf.json.JSONObject;

public class userPageHandler extends Handler{
	UserService service;
	CourseService courseService;
	
	public userPageHandler(MyDBManager dbManager) {
		this.service = new UserService();
		this.courseService = new CourseService();
	}
	
	public ResponseHandler doPost(HttpServletRequest request) {
		System.out.println("Getting the user details (i'm in userpageHandler)");
		
		String sid = request.getParameter("studentId");
		String theId = getBody(request);
		int id = Integer.parseInt(theId);
		User user = service.getUserById(id);
		JSONObject json = new JSONObject();
		json.put("user", user);
		
		return new JSONResponse(json);
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

