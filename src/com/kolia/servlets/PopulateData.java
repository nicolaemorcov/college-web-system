package com.kolia.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kolia.entities.User;
import com.kolia.hibernate.util.MyDBManager;
import com.kolia.services.UserService;

/**
 * Servlet implementation class PopulateData
 */
@WebServlet("/PopulateData")
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
		UserService service = new UserService();
		MyDBManager dbManager = new MyDBManager();
		
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		System.out.println("Getting the data for Kolia........");
		List<User> users = service.getAllUsers();
		System.out.println(users);
		
		Gson gson = new Gson();
		String data = gson.toJson(users);
		out.print(data);
		
		
		
		out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
