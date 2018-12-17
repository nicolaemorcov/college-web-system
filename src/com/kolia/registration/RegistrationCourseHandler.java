package com.kolia.registration;

import com.kolia.handlers.Handler;
import com.kolia.hibernate.util.MyDBManager;

public class RegistrationCourseHandler extends Handler {

	MyDBManager dbManager;

	public RegistrationCourseHandler(MyDBManager dbManager) {
		this.dbManager = dbManager;
	}
	
	
	
}
