package com.kolia.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kolia.entities.Course;
import com.kolia.services.CourseService;

/**
 * Servlet implementation class CourseServlet
 */
public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseServlet() {
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

		CourseService service = new CourseService();
		Course course;
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("courseName");
		int year = Integer.parseInt(request.getParameter("year"));
		int month = Integer.parseInt(request.getParameter("year"));
		int dayOfMonth = Integer.parseInt(request.getParameter("year"));
		String l = request.getParameter("courseLength");
		String f = request.getParameter("tuitionFees");
		double fee = Double.parseDouble(f);
		int length = Integer.parseInt(l);
		course = new Course(name, length, fee, year, month, dayOfMonth);
		service.registerCourse(course);
		request.getRequestDispatcher("home.html").include(request, response);
		out.println("<p> Record saved successfully! </p>");
		Cookie ck[]  = request.getCookies();
		out.println("Here you are again " + ck[0].getValue());
		out.close();
		
		response.sendRedirect("index.html");
	}

}
