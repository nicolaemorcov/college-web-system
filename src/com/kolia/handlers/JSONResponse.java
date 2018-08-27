package com.kolia.handlers;

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
		this.data = json;
		this.contentType = "application/json";
	}
	
	@Override
	public void writeToHttpServletResponse(HttpServletRequest request, 
			HttpServletResponse response) {
		try {
			super.writeToHttpServletResponse(request, response);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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
