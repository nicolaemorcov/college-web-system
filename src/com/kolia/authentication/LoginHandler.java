package com.kolia.authentication;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kolia.entities.User;
import com.kolia.handlers.Handler;
import com.kolia.handlers.ResponseHandler;
import com.kolia.hibernate.util.MyDBManager;
import com.kolia.services.UserService;

public class LoginHandler extends Handler {

	User user;
	MyDBManager dbManager;
	UserService service = new UserService();

	public LoginHandler(MyDBManager dbManager) {
		this.dbManager = dbManager;
	}

	@Override
	public ResponseHandler doPost(HttpServletRequest request) {

		ResponseHandler rh = new ResponseHandler();
		String s;
		try {
			s = IOUtils.toString(request.getReader());
			JsonObject jo = new JsonParser().parse(s).getAsJsonObject();
			System.out.println(s);
			String userId = jo.get("userId").toString().replace("\"", "");
			String password = jo.get("password").toString().replace("\"", "");
			System.out.println(jo.get("userId"));
			System.out.println(jo.get("password"));
			System.out.println(userId.replace("\"", ""));
			System.out.println("sddddddddd");
			boolean authSucceeded = service.authenticateUser(userId, password);
//			user = service.getUserByUserId(userId);
			Cookie c = new Cookie("userId", userId);
			HttpSession session = request.getSession();
			session.setAttribute("user", userId);
			if (!authSucceeded) {
				rh.setStatusCode(302);
				rh.setContentType("text/plain");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rh;

	}

}
