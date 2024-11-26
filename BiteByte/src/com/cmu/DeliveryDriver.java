package com.cmu;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;

public class DeliveryDriver extends User {

	public HashMap<String, Order> ongoingOrders;
	public List<Order> completedOrders;
	
	public DeliveryDriver(String name, String email, String password) {
		super(name, email, password);
		this.ongoingOrders = new HashMap<>();
		this.completedOrders = new ArrayList<>();
		
	}

	public HashMap<String, Order> getOngoingOrders() {
		return ongoingOrders;
	}

	public void addOngoingOrder(String orderID, Order order) {
		this.ongoingOrders.put(orderID, order);
	}

	public List<Order> getCompletedOrders() {
		return completedOrders;
	}

	public void addCompletedOrder(Order order) {
		this.completedOrders.add(order);
	}
}
