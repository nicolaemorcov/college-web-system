package com.kolia.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.kolia.entities.Course;
import com.kolia.hibernate.util.MyDBManager;
import com.kolia.services.CourseService;
import com.opencsv.CSVReader;

@MultipartConfig
public class UploadHandler extends Handler{

	CourseService courseService = new CourseService();
	MyDBManager dbManager;
	
	public UploadHandler(MyDBManager dbManager) {
		this.courseService = new CourseService(dbManager);
		this.dbManager = dbManager;
	}
	
	@Override
	public ResponseHandler doPost(HttpServletRequest request) {
		CSVReader reader = null;
		
		try {
			Part filePart;
			filePart = request.getPart("file");
			// Retrieves <input type="file" name="file">
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
			InputStream fileContent = filePart.getInputStream();
			InputStreamReader isr = new InputStreamReader(fileContent);
			
			reader = new CSVReader(new InputStreamReader(fileContent));
			String[] line;
			while ((line = reader.readNext()) != null) {
				System.out.println(
						"Course [Name: " + line[0] + ", Length: " + line[1] + " Tuition Fee: " + line[2] + "]");
				String courseName = line[0];
				int length = Integer.parseInt(line[1]);
				double tuitionFee = Double.parseDouble(line[2]);
				Course course = new Course(courseName, length, tuitionFee);
				courseService.registerCourse(course);
				
				
			}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ServletException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		ResponseHandler responseHandler = new ResponseHandler();
		// upload was successful redirect to main page
		responseHandler.setStatusCode(301);
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseHandler;
		
	}
	
}
