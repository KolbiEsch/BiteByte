package com.cmu;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DeliveryDriver extends User {

	public HashMap<String, Order> ongoingOrders;
	public List<Order> completedOrders;
	public Deque<Order> pendingOrders;
	
	
	public DeliveryDriver(String name, String email, String password) {
		super(name, email, password);
		this.ongoingOrders = new HashMap<>();
		this.completedOrders = new ArrayList<>();
		this.pendingOrders = new LinkedList<>();
		
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
	public void addPendingOrder(Order order) {
        this.pendingOrders.offer(order); // Add to the queue
    }

    public Order removePendingOrder() {
        return this.pendingOrders.poll(); // Remove from the queue
    }
}
