package com.kolia.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.kolia.entities.Course;
import com.kolia.hibernate.util.MyDBManager;
import com.kolia.services.CourseService;
import com.kolia.services.UserService;

import net.sf.json.JSONObject;

public class coursePageHandler extends Handler{

	UserService service;
	CourseService courseService;
	
	public coursePageHandler(MyDBManager dbManager) {
		this.service = new UserService();
		this.courseService = new CourseService();
	}
	
	public ResponseHandler doPost(HttpServletRequest request) {
		System.out.println("Getting course details .....");
		List<Course> courses = new ArrayList<>();
		courses = courseService.getAllCourses();
		String name = getBody(request);
		Course course = courseService.getCourseByName(name);
		JSONObject json = new JSONObject();
		json.put("courses", courses);
		
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
