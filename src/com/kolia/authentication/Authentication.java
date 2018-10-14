package com.kolia.authentication;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kolia.handlers.Handler;
import com.kolia.handlers.ResponseHandler;
import com.kolia.servlets.Endpoint;

/**
 * Servlet implementation class Authentication
 */
//@WebServlet("/Authentication")
public class Authentication extends Endpoint {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(Authentication.class.getName());
	
	
	@Override
	public void init(ServletConfig config) throws ServletException{
		log.info("Initializing the Auhtentication servlet");
		initializeMapping();
		
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) {
		
		String path = request.getRequestURI();
		log.info("Request " + path + " received on Authentication");
		
		List<String> pathArgs = getPathArgs(path);
		
		Handler handler = findHandler(path, pathArgs);
		
		if(handler == null) {
			log.info("Access Denied");
		}
		else {
			ResponseHandler rh = null;
			if(request.getMethod().equalsIgnoreCase("GET")) {
				rh = handler.doGet(request);
			}
			else if(request.getMethod().equalsIgnoreCase("POST")) {
				rh = handler.doPost(request);
			}
			
			try {
				rh.writeToHttpServletResponse(request, response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}
	
	@Override
	protected void initializeMapping() {
		log.info("Adding the handlers...");
		addHandler(LoginHandler.class, "(?i)^/auth/login");
	}


}
