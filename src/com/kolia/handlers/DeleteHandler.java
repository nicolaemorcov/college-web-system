package com.kolia.handlers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.kolia.entities.Course;
import com.kolia.entities.User;
import com.kolia.hibernate.util.MyDBManager;
import com.kolia.services.UserService;

public class DeleteHandler extends Handler{

	UserService service;
	MyDBManager dbManager;

	public DeleteHandler(MyDBManager dbManager) {
		this.service = new UserService();
		this.dbManager = dbManager;
	}

	
	@Override
	public ResponseHandler doPost(HttpServletRequest request) {
		
		String body = getBody(request);
//		userService.update(u);

		User user = service.getUserByUserId(body);
		service.delete(user);
		
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
