package com.kolia.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.kolia.entities.Course;
import com.kolia.services.CourseService;
import com.opencsv.CSVReader;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CourseService courseService = new CourseService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Part filePart = request.getPart("file");// Retrieves <input type="file" name="file">
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		InputStream fileContent = filePart.getInputStream();
		InputStreamReader isr = new InputStreamReader(fileContent);

		CSVReader reader = null;

		try {
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

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("index.html");

	}

}
