package com.cmu;

public class Customer extends User {

	private Address address;
	public List<Order> orders;
	
	public Customer(String name, String email, String password, String ID) {
		super(name, email, password, ID);
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
