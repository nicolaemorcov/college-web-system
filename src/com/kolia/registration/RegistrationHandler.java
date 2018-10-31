package com.kolia.registration;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kolia.entities.Role;
import com.kolia.entities.User;
import com.kolia.handlers.Handler;
import com.kolia.handlers.ResponseHandler;
import com.kolia.hibernate.util.MyDBManager;
import com.kolia.services.UserService;

public class RegistrationHandler extends Handler {
	
	MyDBManager dbManager;
	
    public RegistrationHandler(MyDBManager dbManager) {
    	this.dbManager = dbManager;
    }


    @Override
	public ResponseHandler doPost(HttpServletRequest request) {
		
    	UserService service = new UserService();
		User user;
		
		String s;
		try {
			s = IOUtils.toString(request.getReader());
			JsonObject jo = new JsonParser().parse(s).getAsJsonObject();
			System.out.println(s);
			String firstName = jo.get("firstName").toString().replace("\"", "");
			String lastName = jo.get("lastName").toString().replace("\"", "");
			String email = jo.get("email").toString().replace("\"", "");
			String userId = jo.get("userId").toString().replace("\"", "");
			String password = jo.get("password").toString().replace("\"", "");
			String role = jo.get("role").toString().replace("\"", "");

			user = new User(firstName, lastName, email, userId, password, Role.valueOf(role).toString());

			boolean result = service.registerUser(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return new ResponseHandler();
	}

}
