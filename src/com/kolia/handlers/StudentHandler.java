package com.kolia.handlers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.kolia.entities.User;
import com.kolia.hibernate.util.MyDBManager;
import com.kolia.services.UserService;

import net.sf.json.JSONObject;

public class StudentHandler extends Handler{
	
	UserService userService;
	MyDBManager dbManager;
	
	public StudentHandler(MyDBManager dbManager) {
		this.userService = new UserService();
		this.dbManager = dbManager;
		
	}
	
	public ResponseHandler doPost(HttpServletRequest request) {
		System.out.println("Getting the user details (i'm in StudentHandler)");
		
		String sid = request.getParameter("studentId");
		String userId = getBody(request).toString().replaceAll("\"", "");
		User user = userService.getUserByUserId(userId);
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
