package com.kolia.service;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kolia.entities.User;
import com.kolia.hibernate.util.MyDBManager;

public class UserServiceTest {
	MyDBManager dbManager;
	
	
	@Before
	public void setUp() throws Exception {
		dbManager = new MyDBManager();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public Map<Integer, User> getMappedUsers(List<User> uaa){
		HashMap<Integer, User> users = new HashMap<>();
		for(User u: uaa) {
			Integer id = u.getId();
			User user = u;
			users.put(id, user);
		}
		System.out.println(users);
		return users;
		
	}

}
