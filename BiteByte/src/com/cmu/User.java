package com.cmu;

public class User {
	private String name;
	private String email;
	private String password;
	private String ID;
	
	
	public User(String name, String email, String password, String iD) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		ID = iD;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	
	
}
