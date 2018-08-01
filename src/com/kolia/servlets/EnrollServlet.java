package com.kolia.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kolia.entities.Course;
import com.kolia.entities.User;
import com.kolia.services.CourseService;
import com.kolia.services.UserService;

/**
 * Servlet implementation class EnrollServlet
 */
public class EnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserService service = new UserService();
		CourseService cService = new CourseService();;
		User user = new User();
		Course course;
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String userId = request.getParameter("userId");
		String courseName = request.getParameter("course");
		user = service.getUserByUserId(userId);
		course = cService.getCourseByName(courseName);
		user.setCourse(course);
		service.update(user);
		RequestDispatcher dispatcher = request.getRequestDispatcher("main.htm");
		dispatcher.forward(request, response);
//		out.close();
		
	}

}
