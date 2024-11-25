package com.cmu;

import java.util.Deque;
import java.util.HashMap;
import java.util.List;

public class DeliveryDriver extends User {

	public HashMap<String, Order> ongoingOrders;
	public Deque<Order> orderQueue;
	public List<Order> completedOrders;
	
	public DeliveryDriver(String name, String email, String password) {
		super(name, email, password);
	}
}
