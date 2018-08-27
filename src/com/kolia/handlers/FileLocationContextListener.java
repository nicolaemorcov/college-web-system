package com.kolia.handlers;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class FileLocationContextListener  implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent servltContextEvent) {
		String rootPath = System.getProperty("catalina.home");
		ServletContext ctx = servltContextEvent.getServletContext();
		String relativePath = ctx.getInitParameter("students3.csv");
		File file = new File(rootPath + File.separator + relativePath);
		if(!file.exists()) {
			file.mkdirs();
		}
		System.out.println("File directory created to be used tfo storing files");
		ctx.setAttribute("FILES_DIR_FILE", file);
		ctx.setAttribute("FILES_DIR", rootPath + File.separator + relativePath);
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}
	

}
