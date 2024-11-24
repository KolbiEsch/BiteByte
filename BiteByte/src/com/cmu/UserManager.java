package com.cmu;

import java.util.ArrayList;
import java.util.List;

class UserManager {
	
	private static UserManager instance = null;
	private List<User> users;
	private int currentID;
	
	private UserManager() {
		users = new ArrayList<>();
		currentID = 1;
	}
	
	public static UserManager getInstance() {
		if (instance == null) {
			instance = new UserManager();
		}
		return instance;
	}
	
	public User getCurrentUser() {
		for (User user : users) { 
			if (user.getSignedIn()) {
				return user;
			}
		}
		return null;
	}
	
	public User createUser(User user) {
		user.setID(currentID);
		updateID();
		users.add(user);
		return user;
	}
	
	private void updateID() {
		currentID++;
	}
	
	public List<User> getUsers() {
		return this.users;
	}
	
	public User getUserByEmail(String email) {
		return users.stream()
				.filter(user -> user.getEmail().equals(email))
				.findFirst()
				.orElse(null);
	}
}
