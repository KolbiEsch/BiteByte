package com.cmu;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    // Attributes of the Order class
    private String orderId; 
    private Customer customer; 
    private Restaurant restaurant; 
    private List<Item> items; 
    private double totalPrice; 
    private String status; 
    private LocalDateTime orderTime; 

    // Constructor to initialize an Order object
    public Order(String orderId, Customer customer, Restaurant restaurant) {
        this.orderId = orderId;
        this.customer = customer;
        this.restaurant = restaurant;
        this.items = new ArrayList<>(); 
        this.totalPrice = 0.0;
        this.status = "pending";  
    }

    // Getters and setters for each attribute
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    // Method to add an item to the order
    public void addItems(Item item) {
        items.add(item); // Adds the item to the list
        calculateTotal(); // Updates the total price after adding the item
    }

    private void calculateTotal() {
        totalPrice = 0.0; // Initialize total price to zero
        for (Item item : items) { // Loop through each item in the list
            totalPrice += item.getPrice(); // Add the price of each item to the total price
        }
    }

}
