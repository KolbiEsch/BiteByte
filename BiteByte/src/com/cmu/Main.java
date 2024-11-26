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
import java.util.Collections;

public class Main extends Application {

    private static UserManager userManager = UserManager.getInstance();
    private Restaurant restaurant;

    @Override
    public void start(Stage primaryStage) {
        /*
         * BorderPane is the main pane. When you want to switch panes using nav
         * have the action of the button be to set that pane to BorderPane center.
         * See NavbarPane class for an example.
         */
        BorderPane pane = new BorderPane();
        
        LoginPane loginPane = new LoginPane(pane);
        pane.setCenter(loginPane.getPane());
        
        Scene scene = new Scene(pane, 400, 300);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("Restaurant App");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // Kolbi: I commented out this code so the program will work until it can be moved into it's own pane class.
		/*
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
	*/
        
    }
    
    public static void createCustomers() {
    	Customer customerDemo1 = new Customer("Customer Demo 1", "customerdemo1@gmail.com", "customer1pass");
    	Customer customerDemo2 = new Customer("Customer Demo 2", "customerdemo2@gmail.com", "customer2pass");
    	Customer customerDemo3 = new Customer("Customer Demo 3", "customerdemo3@gmail.com", "customer3pass");
    	Customer customerDemo4 = new Customer("Customer Demo 4", "customerdemo4@gmail.com", "customer4pass");
    	Customer customerDemo5 = new Customer("Customer Demo 5", "customerdemo5@gmail.com", "customer5pass");
    	userManager.createUser(customerDemo1);
    	userManager.createUser(customerDemo2);
    	userManager.createUser(customerDemo3);
    	userManager.createUser(customerDemo4);
    	userManager.createUser(customerDemo5);
    }
    
    public static void createDrivers() {
    	DeliveryDriver driverDemo1 = new DeliveryDriver("Delivery Demo 1", "deliverydemo1@gmail.com", "delivery1pass");
    	DeliveryDriver driverDemo2 = new DeliveryDriver("Delivery Demo 2", "deliverydemo2@gmail.com", "delivery2pass");
    	DeliveryDriver driverDemo3 = new DeliveryDriver("Delivery Demo 3", "deliverydemo3@gmail.com", "delivery3pass");
    	DeliveryDriver driverDemo4 = new DeliveryDriver("Delivery Demo 4", "deliverydemo4@gmail.com", "delivery4pass");
    	DeliveryDriver driverDemo5 = new DeliveryDriver("Delivery Demo 5", "deliverydemo5@gmail.com", "delivery5pass");
    	userManager.createUser(driverDemo1);
    	userManager.createUser(driverDemo2);
    	userManager.createUser(driverDemo3);
    	userManager.createUser(driverDemo4);
    	userManager.createUser(driverDemo5);
    }
    
    public static Restaurant getFastFoodRestaurant() {
    	ArrayList<Item> fastFoodMenu = new ArrayList<>();
    	Item item1 = new Item("Burger", 9.99, true);
        Item item2 = new Item("Fries", 3.99, true);
        Item item3 = new Item("Drink", 2.99, true);
        Collections.addAll(fastFoodMenu, item1, item2, item3);
        Restaurant fastFood = new Restaurant("Fast Food Joint", "Fast Food", "1", fastFoodMenu);
    	
    	return fastFood;
    }
    
    public static Restaurant getApplebees() {
    	ArrayList<Item> applebeesMenu = new ArrayList<>();
    	Item item1 = new Item("8 oz Top Sirloin", 18.49, true);
    	Item item2 = new Item("Whiskey Bacon Burger", 14.49, true);
    	Item item3 = new Item("Neighborhood Burger", 12.49, true);
    	Collections.addAll(applebeesMenu, item1, item2, item3);
    	
    	Restaurant applebees = new Restaurant("Applebees", "Casual Dining", "2", applebeesMenu);
    	
    	return applebees;
    }
    
    public static Restaurant getTexasRoadhouse() {
    	ArrayList<Item> texasRoadhouseMenu = new ArrayList<>();
    	Item item1 = new Item("Cactus Blossom", 8.99, true);
    	Item item2 = new Item("12 oz New York Strip", 24.99, true);
    	Item item3 = new Item("Grilled Chicken Salad", 14.49, true);
    	
    	Collections.addAll(texasRoadhouseMenu, item1, item2, item3);
    	
    	Restaurant texasRoadhouse = new Restaurant("Texas Roadhouse", "Casual Dining", "3", texasRoadhouseMenu);
    	
    	return texasRoadhouse;
    }
    
    public static void createOrders() {
    	User customer1 = userManager.getUserByEmail("customerdemo1@gmail.com");
    	Order order1 = new Order("1", customer1, getFastFoodRestaurant());
    	order1.addItems(order1.getRestaurant().getMenu().get(0));
    	order1.addItems(order1.getRestaurant().getMenu().get(1));
    	
    	Order order2 = new Order("2", customer1, getTexasRoadhouse());
    	order2.addItems(order2.getRestaurant().getMenu().get(0));
    	order2.addItems(order2.getRestaurant().getMenu().get(1));
    	order2.setStatus("completed");
    	
    	DeliveryDriver deliveryDriver1 = userManager.getDriverByEmail("deliverydemo1@gmail.com");
    	deliveryDriver1.addCompletedOrder(order2);
    	deliveryDriver1.addOngoingOrder(order1.getOrderId(), order1);
    }
    
    public static void main(String args[]) {
    	createCustomers();
    	createDrivers();
    	createOrders();
    	launch(args);
    }
}
