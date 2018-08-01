package com.kolia.servlets;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kolia.handlers.EditHandler;
import com.kolia.handlers.Handler;
import com.kolia.handlers.ResponseHandler;
import com.kolia.handlers.userPageHandler;
import com.kolia.handlers.HomeHandler;

/**
 * Servlet implementation class ServiceEndPoint
 */
public class ServiceEndPoint extends Endpoint {
	private static final long serialVersionUID = 1L;
       
	private static final Logger log = Logger.getLogger(ServiceEndPoint.class.getName());
	

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    @Override
	public void init(ServletConfig config) throws ServletException {
		log.info("Initializing the ServiceEndPoint servlet");
		initializeMapping();
	}
    
    protected void service(HttpServletRequest request, HttpServletResponse response) {
    	
    	String path = request.getRequestURI();
    	
    	log.info("Request " + path + " received on ServiceEndPoint");
    	
    	// here will be stored all path arguments
    	List<String> pathArgs = getPathArgs(path);
    	
    	Handler handler = findHandler(path, pathArgs);
    	
    	if (handler == null) {
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
    		rh.writeToHttpServletResponse(request, response);
    	}
    	
    	
    	
    }
    
    @Override
	protected void initializeMapping() {
		log.info("adding the handlers");
		addHandler(HomeHandler.class, "(?i)^/services/users");
		addHandler(EditHandler.class, "(?i)^/services/edit");
		addHandler(userPageHandler.class, "(?i)^/services/userDetails");
	}
	
}
