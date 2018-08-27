package com.kolia.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kolia.entities.User;
import com.kolia.hibernate.util.MyDBManager;
import com.kolia.services.CourseService;
import com.kolia.services.UserService;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class PopulateData
 */
public class PopulateData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PopulateData() {
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
		List<User> users = userService.getAllUsers();
		System.out.println(users);
		JSONObject json = new JSONObject();
		json.put("users", users);
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

		
//		out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
