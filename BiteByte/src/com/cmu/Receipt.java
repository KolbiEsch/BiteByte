package com.cmu;

public class Receipt {
    private Order order;
    private double totalPaid;

    //Constructor
    public Receipt(Order order, double totalPaid) {
        this.order = order;
        this.totalPaid = totalPaid;
    }

    public Order getOrder() {
        return order;
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    // Method to create the receipt
    public String generateReceiptDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Order ID: ").append(order.getOrderId()).append("\n")
               .append("Total Paid: $").append(String.format("%.2f", totalPaid)).append("\n\n")
               .append("Items:\n");

        // Loop to iterate through the items and output on the receipt
        for (int i = 0; i < order.getItems().size(); i++) {
            Item item = order.getItems().get(i);
            details.append(item.getName()).append(" - $").append(item.getPrice()).append("\n");
        }

        return details.toString();
    }
}
