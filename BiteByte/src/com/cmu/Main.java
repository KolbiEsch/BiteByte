package com.cmu;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Main extends Application {

    private UserManager userManager = UserManager.getInstance();
    private Restaurant restaurant;

    @Override
    public void start(Stage primaryStage) {
        // Sample User and Restaurant Data
        Customer user = new Customer("John Doe", "john@example.com", "password123");
        user.setSignedIn(true);
        userManager.createUser(user);
        
        /*
         * BorderPane is the main pane. When you want to switch panes using nav
         * have the action of the button be to set that pane to BorderPane center.
         * See below for examples.
         */
        BorderPane pane = new BorderPane();
		HBox navbar = new HBox();
		
		Button profileBtn = new Button("Profile");
		navbar.setMargin(profileBtn, new Insets(0, 10, 0, 0));
		Button restaurantsBtn = new Button("Restaurants");
		
		navbar.setPadding(new Insets(15));
		navbar.getChildren().addAll(profileBtn, restaurantsBtn);
		
		pane.setTop(navbar);
		
		// Example of BorderPane nav relationship.
		profileBtn.setOnAction(e -> {
			ProfilePane profile = new ProfilePane();
			pane.setCenter(profile.getPane());
		});

        // Setting up the Restaurant menu
        Item item1 = new Item("Burger", 5.99, true);
        Item item2 = new Item("Fries", 2.49, true);
        Item item3 = new Item("Soda", 1.99, true);

        ArrayList<Item> menu = new ArrayList<>();
        menu.add(item1);
        menu.add(item2);
        menu.add(item3);

        restaurant = new Restaurant("Burger Joint", "Fast Food", "001", menu);
        

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
            Order order = new Order("ORD001", user, restaurant);

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
        
        restaurantsBtn.setOnAction(e -> {
        	pane.setCenter(root);
        });

        Scene scene = new Scene(pane, 400, 300);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("Restaurant App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String args[]) {
    	launch(args);
    }
}
