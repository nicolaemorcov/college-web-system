package com.kolia.services;



import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

public class JSONResponse extends ResponseHandler{
	private JSON data;
	private String status = "OK";
	
	public JSONResponse() {
	}

	public JSONResponse(JSON json) {
		this.contentType = "application/json";
		this.data = json;
	}
	
	public void writeToHttpServletResponse(HttpServletRequest request,
			HttpServletResponse response) {
		super.writeToHttpServletResponse(request, response);
		
		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put("status", status);
		if(data != null) {
			jsonResponse.put("data", data);
		}
		
		try {
			response.getOutputStream()
			.write(jsonResponse.toString().getBytes("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
