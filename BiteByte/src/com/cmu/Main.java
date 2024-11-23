package com.cmu;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    private UserManager userManager = UserManager.getInstance();
    private Restaurant restaurant;

    @Override
    public void start(Stage primaryStage) {
        // Sample User and Restaurant Data
        User user = new User("John Doe", "john@example.com", "password123");
        userManager.createUser(user);

        // Setting up the Restaurant menu
        Item item1 = new Item("Burger", 5.99);
        Item item2 = new Item("Fries", 2.49);
        Item item3 = new Item("Soda", 1.99);

        ArrayList<Item> menu = new ArrayList<>();
        menu.add(item1);
        menu.add(item2);
        menu.add(item3);

        restaurant = new Restaurant("Burger Joint", "Fast Food", "001", menu);

        // JavaFX UI Components
        primaryStage.setTitle("Restaurant App");

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        // Restaurant Label
        Label restaurantLabel = new Label("Restaurant: " + restaurant.getName() + " (" + restaurant.getType() + ")");
        restaurantLabel.getStyleClass().add("label");

        // Menu Label
        Label menuLabel = new Label("Menu:");
        menuLabel.getStyleClass().add("label");

        // ListView for Menu Items
        ListView<String> menuListView = new ListView<>();
        for (Item item : restaurant.getMenu()) {
            menuListView.getItems().add(item.getName() + " - $" + item.getPrice());
        }
        menuListView.getStyleClass().add("list-view");

        // Select Item Button
        Button selectButton = new Button("Select Item");
        selectButton.getStyleClass().add("button");
        selectButton.setOnAction(event -> {
            String selectedItem = menuListView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                System.out.println("You selected: " + selectedItem);
            }
        });

        // Create Order Button
        Button createOrderButton = new Button("Create Order");
        createOrderButton.getStyleClass().add("button");
        createOrderButton.setOnAction(event -> {
            // Create an order with selected items
            ArrayList<Item> selectedItems = new ArrayList<>();
            for (Item item : restaurant.getMenu()) {
                selectedItems.add(item);  // For demo, adding all items
            }
            Order order = new Order("ORD001", selectedItems);

            // Generate receipt
            Receipt receipt = new Receipt(order, 10.47); // Example total paid
            System.out.println(receipt.generateReceiptDetails());
        });

        // Layout for the UI
        root.getChildren().addAll(
                restaurantLabel,
                menuLabel,
                menuListView,
                selectButton,
                createOrderButton
        );

        Scene scene = new Scene(root, 400, 300);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
