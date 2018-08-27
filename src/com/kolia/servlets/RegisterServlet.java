package com.kolia.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kolia.entities.User;
import com.kolia.services.UserService;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService service = new UserService();
		User user;

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");

		user = new User(firstName, lastName, email, userId, password);

		boolean result = service.registerUser(user);
		

		out.println("<html>");
		out.println("<head>");
		out.println("<title>Registration Successful</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		if (result) {
		out.println("<h1>Thanks for Registering with us :</h1>");
		out.println("To login with new UserId and Password<a href=\"login.html\">Click here</a>");
		System.out.println("User saved...");
		}
		else {
			out.println("<h1>Registration Failed</h1>");
            out.println("To try again<a href=\"register.html\">Click here</a>");
            System.out.println("User exists!");
		}
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		out.close();

	}

}
