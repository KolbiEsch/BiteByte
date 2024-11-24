package com.cmu;

public class SignInManager {
	
	private static UserManager userManager = UserManager.getInstance();
	
	public SignInManager() {
	}

	public static User signIn(String email, String password) {
		User user = userManager.getUserByEmail(email);
		if (user == null) {
			return null;
		}
		
		if (!user.getPassword().equals(password)) {
			return null;
		}
		return user;
	}
	
}
