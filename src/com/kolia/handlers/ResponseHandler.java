package com.kolia.handlers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResponseHandler {
	
	protected int statusCode;
	protected String contentType;
	
	public ResponseHandler() {
		statusCode = 200;
		contentType = "text/plain";
	}
	
	public ResponseHandler setStatusCode(int statusCode) {
		this.statusCode = statusCode;
		return this;
	}
	public ResponseHandler setContentType(String contentType) {
		this.contentType = contentType;
		return this;
	}
	
	public void writeToHttpServletResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setStatus(statusCode);
		response.setContentType(contentType);
//		if(statusCode == 301) {
//			response.sendRedirect("services/users");
//		}
	}
	
	
}
