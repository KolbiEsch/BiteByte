package com.cmu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class DeliveryDriverPane implements BasePane {

    private DeliveryDriver driver;

    public DeliveryDriverPane(DeliveryDriver driver) {
        this.driver = driver;
    }

    @Override
    public Pane getPane() {
        // Create the main BorderPane layout
        BorderPane borderPane = new BorderPane();

        // Driver name on the top
        Label driverName = new Label("Driver: " + driver.getName());

        // HBox to hold driver label
        HBox driverInfo = new HBox();
        driverInfo.setAlignment(Pos.CENTER);
        driverInfo.getChildren().add(driverName);
        driverInfo.setMaxHeight(12);

        // VBox to hold pending, ongoing, and completed orders
        VBox ordersBox = new VBox(10);
        ordersBox.getChildren().addAll(getPendingOrders(), getOngoingOrders(), getCompletedOrders());
        ordersBox.setAlignment(Pos.CENTER);
        ordersBox.setMaxWidth(315);
        ordersBox.setMaxHeight(650);

        // Adds the label, and the ordersBox containing all the list views to the border pane
        borderPane.setTop(driverInfo);
        borderPane.setCenter(ordersBox);
        BorderPane.setAlignment(ordersBox, Pos.CENTER);

        return borderPane;
    }

    //Method for the ongoing orders
    public Pane getOngoingOrders() {
        // ListView to display ongoing orders
        ListView<Order> ongoingOrders = new ListView<>();
        ongoingOrders.setPrefHeight(175);

        // Button to refresh the ongoing orders list
        Button btnOngoing = new Button("Refresh Current Orders");
        btnOngoing.setMaxSize(350, 30);

        btnOngoing.setOnAction(e -> {
            ongoingOrders.getItems().clear();
            ongoingOrders.getItems().addAll(driver.ongoingOrders.values());
        });

        // displays information into the list view
        ongoingOrders.setCellFactory(e -> new ListCell<Order>() {
            @Override
            protected void updateItem(Order order, boolean empty) {
                super.updateItem(order, empty);
                if (empty || order == null) {
                    setText(null);
                } else {
                    setText("Order ID: " + order.getOrderId() + ", Customer: " + order.getCustomer().getName() + ", Status: " + order.getStatus());
                }
            }
        });

        // Creates the complete order button
        Button btnAdd = new Button("Complete Order");
        btnAdd.setMaxWidth(250);
        btnAdd.setMaxHeight(20);

        // hbox to hold the complete order button
        HBox addHbox = new HBox();
        addHbox.getChildren().add(btnAdd);
        addHbox.setAlignment(Pos.CENTER);

        //action to for the Add button to move the order into the completed order list view
        btnAdd.setOnAction(e -> {
            Order selectedOrder = ongoingOrders.getSelectionModel().getSelectedItem();
            if (selectedOrder != null) {
                driver.ongoingOrders.remove(selectedOrder.getOrderId());
                driver.addCompletedOrder(selectedOrder);
                ongoingOrders.getItems().remove(selectedOrder);
            }
        });

        // VBox to hold the listview and buttons
        VBox vBox = new VBox(10);
        vBox.setMaxWidth(350);
        vBox.setMaxHeight(175);
        vBox.getChildren().addAll(btnOngoing, ongoingOrders, addHbox);

        return vBox;
    }

    //Method for the completed orders
    public Pane getCompletedOrders() {
        // ListView for completed orders
        ListView<Order> completedOrders = new ListView<>();
        completedOrders.setPrefHeight(150);

        // Button to refresh the completed orders list
        Button btnCompleted = new Button("Refresh Completed Orders");
        btnCompleted.setMaxSize(350, 20);

        //action to refresh the compeleted orders
        btnCompleted.setOnAction(e -> {
            completedOrders.getItems().clear();
            completedOrders.getItems().addAll(driver.getCompletedOrders());
        });

        // Make the list view work with the Order type
        completedOrders.setCellFactory(e -> new ListCell<Order>() {
            @Override
            protected void updateItem(Order order, boolean empty) {
                super.updateItem(order, empty);
                if (empty || order == null) {
                    setText(null);
                } else {
                    setText("Order ID: " + order.getOrderId() +
                            ", Customer: " + order.getCustomer().getName() +
                            ", Status: " + order.getStatus());
                }
            }
        });

        // VBox to add button and list view
        VBox vBox = new VBox();
        vBox.setMaxWidth(350);
        vBox.setMaxHeight(170);
        vBox.getChildren().addAll(btnCompleted, completedOrders);

        return vBox;
    }

    //Method for the pending orders
    public Pane getPendingOrders() {
        // ListView to display pending orders
        ListView<Order> pendingOrders = new ListView<>();
        pendingOrders.setPrefWidth(350);
        pendingOrders.setPrefHeight(175);

        // Button to refresh the pending orders 
        Button btnPending = new Button("Refresh Pending Orders");
        btnPending.setMaxSize(350, 30);

        // Creates the button to more an order to ongoing
        Button btnAddToOngoing = new Button("Add to Orders");
        btnAddToOngoing.setMaxWidth(250);
        btnAddToOngoing.setMaxHeight(20);

        // hbox to hold the btnAdd
        HBox addToOngoingHbox = new HBox();
        addToOngoingHbox.getChildren().add(btnAddToOngoing);
        addToOngoingHbox.setAlignment(Pos.CENTER);

        
        btnPending.setOnAction(e -> {
            pendingOrders.getItems().clear();
            pendingOrders.getItems().addAll(driver.pendingOrders);
        });

        //Adds the orders to the ongoing orders
        btnAddToOngoing.setOnAction(e -> {
            Order selectedOrder = pendingOrders.getSelectionModel().getSelectedItem();
            if (selectedOrder != null) {
                driver.pendingOrders.remove(selectedOrder);
                driver.addOngoingOrder(selectedOrder.getOrderId(), selectedOrder);
                pendingOrders.getItems().remove(selectedOrder);
            }
        });

       
        pendingOrders.setCellFactory(e -> new ListCell<Order>() {
            @Override
            protected void updateItem(Order order, boolean empty) {
                super.updateItem(order, empty);
                if (empty || order == null) {
                    setText(null);
                } else {
                    setText("Order ID: " + order.getOrderId() +
                            ", Customer: " + order.getCustomer().getName());
                }
            }
        });

        // VBox to hold buttons and list view
        VBox vBox = new VBox(10);
        vBox.setMaxWidth(350);
        vBox.setMaxHeight(175);
        vBox.getChildren().addAll(btnPending, pendingOrders, addToOngoingHbox);

        return vBox;
    }
}
