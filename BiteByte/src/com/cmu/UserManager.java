package com.cmu;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
	
	public Customer getCurrentCustomer() {
		for (User user : users) {
			if (user instanceof Customer && user.getSignedIn()) {
				return (Customer) user;
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
	
	public Thread setUserPasswordAsync(String password) {
		Alert successAlert = new Alert(AlertType.INFORMATION);
		successAlert.setTitle("Password Update Successful");
		successAlert.setContentText("Password was successfully updated");
		
		Alert errorAlert = new Alert(AlertType.ERROR);
		errorAlert.setTitle("Unable to Update Password");
		errorAlert.setContentText("Error updating the password");
		
		Thread updateUserPasswordThread = new Thread(() -> {
			User currentUser = getCurrentUser();
			// Simulate fetching from a database
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (currentUser != null && password.length() > 0) {
				currentUser.setPassword(password);
				Platform.runLater(() -> successAlert.showAndWait());
			} else {
				Platform.runLater(() -> errorAlert.showAndWait());
			}
		});
		return updateUserPasswordThread;
	}
	
	public Thread setCustomerAddressAsync(Address address) {
		Alert successAlert = new Alert(AlertType.INFORMATION);
		successAlert.setContentText("Address was sucessfully updated");
		successAlert.setTitle("Address Updated");
		
		Alert errorAlert = new Alert(AlertType.ERROR);
		errorAlert.setContentText("Error updating address");
		errorAlert.setTitle("Address Error");
		
		Thread setCustomerAddressThread = new Thread(() -> {
			Customer currentCustomer = getCurrentCustomer();
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (currentCustomer != null && address != null) {
				currentCustomer.setAddress(address);
				Platform.runLater(() -> successAlert.showAndWait());
			} else {
				Platform.runLater(() -> errorAlert.showAndWait());
			}
		});
		
		return setCustomerAddressThread;
	}
	
	public Customer getCustomerByEmail(String email) {
		return (Customer) users.stream()
				.filter(user -> user instanceof Customer)
				.filter(user -> user.getEmail().equals(email))
				.findFirst()
				.orElse(null);
	}
	
	public DeliveryDriver getDriverByEmail(String email) {
		return (DeliveryDriver) users.stream()
		.filter(user -> user instanceof DeliveryDriver)
		.filter(user -> user.getEmail().equals(email))
		.findFirst()
		.orElse(null);
	}
}
