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
 * Servlet implementation class EditServlet
 */
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService service = new UserService();
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);
		User user = service.getUserById(id);
		pw.println("<h1>Update User</h1>");
		pw.print("<form action='EditServlet2' method='post'>");  
        pw.print("<table>");  
        pw.print("<tr><td></td><td><input type='hidden' name='id' value='"+ user.getId() +"'/></td></tr>");  
        pw.print("<tr><td>User Id:</td><td><input type='text' name='userId' value='"+ user.getUserId() + "'/></td></tr>");  
        pw.print("<tr><td>Password:</td><td><input type='password' name='password' value='"+ user.getPassword() + "'/></td></tr>");  
        pw.print("<tr><td>First Name:</td><td><input type='text' name='firstName' value='"+ user.getFirstName() +"'/></td></tr>");  
        pw.print("<tr><td>Last Name:</td><td><input type='text' name='lastName' value='"+ user.getLastName() +"'/></td></tr>");  
        pw.print("<tr><td>Email:</td><td><input type='email' name='email' value='"+ user.getEmail() +"'/></td></tr>");  
        pw.print("</td></tr>");  
        pw.print("<tr><td colspan='2'><input type='submit' value='Edit & Save '/></td></tr>");  
        pw.print("</table>");  
        pw.print("</form>");
        service.update(user);
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		
	}

}
