package com.kolia.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kolia.entities.Course;
import com.kolia.hibernate.util.MyDBManager;
import com.kolia.services.CourseService;
import com.kolia.services.UserService;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class PopulateCourses
 */
public class PopulateCourses extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PopulateCourses() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService userService = new UserService();
		CourseService courseService = new CourseService();
		MyDBManager dbManager = new MyDBManager();
		
		
		
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
		
		System.out.println("Getting the data for Kolia........");
		List<Course> courses = courseService.getAllCourses();
		System.out.println(courses);
		JSONObject json = new JSONObject();
		json.put("courses", courses);
//		Gson gson = new Gson();
//		String data = gson.toJson(users);
//		out.print(data);
		try {
			response.getOutputStream()
			.write(json.toString().getBytes("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
