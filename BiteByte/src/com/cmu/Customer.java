package com.cmu;

import java.util.List;

public class Customer extends User {

	private Address address;
	public List<Order> orders;
	
	public Customer(String name, String email, String password) {
		super(name, email, password);
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
