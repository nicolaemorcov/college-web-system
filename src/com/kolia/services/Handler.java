package com.kolia.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Handler {
	
	public Handler() {}
	private static final Logger log = Logger.getLogger(Handler.class.getName());

	protected boolean hasParameter(HttpServletRequest request, String paramName) {
		return request.getParameter(paramName) != null;
	}
	
	

	public ResponseHandler doGet(HttpServletRequest request) {
		throw new RuntimeException("Does not support GET method");
	}

	public ResponseHandler doPost(HttpServletRequest request) {
		throw new RuntimeException("Does not support POST method");
	}

	public HttpServletResponse doPut(HttpServletRequest request) {
		throw new RuntimeException("Does not support PUT method");
	}

	public HttpServletResponse doDelete(HttpServletRequest request) {
		throw new RuntimeException("Does not support DELETE method");
	}
	
protected List<String> getPathArgs(String path) {
		
		String[] pathArguments = path.split("/");
		
		List<String> pathArgs = new ArrayList<String>();
		for (int i = 1; i < pathArguments.length; i++) {
			pathArgs.add(pathArguments[i]);
		}
		
		return pathArgs;
	}
}
