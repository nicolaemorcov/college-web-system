package com.kolia.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kolia.entities.User;
import com.kolia.services.UserService;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		UserService service = new UserService();
		User user;
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		boolean result = service.authenticateUser(userId, password);
		user = service.getUserByUserId(userId);
		if(result == true) {
			request.getSession().setAttribute("userId", userId);
			request.getRequestDispatcher("home.html").include(request, response);
			out.println("Hi " + userId);
			Cookie c = new Cookie("userId", userId);
			response.addCookie(c);
		}
		else {
			response.sendRedirect("error.html");
		}
		
	}

}
