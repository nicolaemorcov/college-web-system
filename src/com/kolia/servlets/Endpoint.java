package com.kolia.servlets;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kolia.handlers.EditHandler;
import com.kolia.handlers.Handler;
import com.kolia.handlers.ResponseHandler;
import com.kolia.handlers.HomeHandler;
import com.kolia.hibernate.util.MyDBManager;

/**
 * Servlet implementation class Endpoint
 */
public class Endpoint extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MyDBManager dbManager = new MyDBManager();

	// Logger for requests
	private static final Logger log = Logger.getLogger(Endpoint.class.getName());

	private Map<Pattern, Class<? extends Handler>> handlerMapping = new LinkedHashMap<Pattern, Class<? extends Handler>>();

	@Override
	public void init(ServletConfig config) throws ServletException {
		log.info("Initializing the Endpoint servlet");
		initializeMapping();

	}

	protected void service(HttpServletRequest request, HttpServletResponse response) {
		
		String path = request.getRequestURI();
		
		// here will store all path arguments
		List<String> pathArgs = getPathArgs(path);
		Handler handler = findHandler(path, pathArgs);
		
		if(handler == null) {
			log.info("Access denied Blia");
		}
		else {
			ResponseHandler rh = handler.doGet(request);
			rh.writeToHttpServletResponse(request, response);
		}
		
	}

	protected Handler findHandler(String path, List<String> pathArgs) {
		if (path.endsWith("/")) {
			path = path.replaceAll("/+$", "");
		}
		System.out.println(handlerMapping.get(path));
		log.info("Path is " + path);
		
		for (Entry<Pattern, Class<? extends Handler>> entry : handlerMapping.entrySet()) {
			System.out.println(entry.getKey().toString());
			System.out.println(handlerMapping.get(entry.getKey()));
			Matcher matcher = entry.getKey().matcher(path);
			log.info("Matcher is " + entry.getKey());

			if (matcher.find()) {
				for (int i = 0; i < matcher.groupCount(); i++) {
					if (matcher.group(i + 1) != null) {
						pathArgs.add(matcher.group(i + 1));
					}
				}
				Class<? extends Handler> handlerClass = entry.getValue();

				log.info("Class " + handlerClass.getName() + " is found");

				// return handler
				switch (handlerClass.getName()) {
				case "com.kolia.handlers.HomeHandler":
					return new HomeHandler(dbManager);
				case "com.kolia.handlers.EditHandler":
					return new EditHandler(dbManager);
				}

			}
		}
		return null;
	}

	protected List<String> getPathArgs(String path) {
		// if I have the url services/users/userId
		// it will be split into 3 items in array
		// [services, users, userId]
		String[] pathArguments = path.split("/");

		List<String> pathArgs = new ArrayList<String>();
		for (int i = 1; i < pathArguments.length; i++) {
			pathArgs.add(pathArguments[i]);
		}
		return pathArgs;
	}

	protected void initializeMapping() {
		log.info("adding the handlers");

		// here will be the handlers for something else
	}
	
	protected void addHandler(Class<? extends Handler> handlerClass, String urlPattern) {
		Pattern pattern = Pattern.compile(urlPattern);
		handlerMapping.put(pattern, handlerClass);
	}

}
