package com.kolia.authentication;

import javax.servlet.http.HttpServletRequest;

import com.kolia.handlers.Handler;
import com.kolia.handlers.ResponseHandler;
import com.kolia.hibernate.util.MyDBManager;

public class LogoutHandler extends Handler{

	MyDBManager dbManager;
	
	public LogoutHandler(MyDBManager dbManager) {
		this.dbManager = dbManager;
	}
	
	public ResponseHandler doGet(HttpServletRequest request) {
		
		
		return new ResponseHandler();
	}
	
}
