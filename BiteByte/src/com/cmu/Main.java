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

    // Singleton instance of UserManager to manage user-related operations.
    private static UserManager userManager = UserManager.getInstance();
    private Restaurant restaurant; // Field for a Restaurant object

    @Override
    public void start(Stage primaryStage) {
        /*
         * BorderPane is the main pane. When you want to switch panes using nav
         * have the action of the button be to set that pane to BorderPane center.
         * See NavbarPane class for an example.
         */

        ScrollPane scrollContainer = new ScrollPane(); // Creates a ScrollPane to allow scrolling for large content.
        BorderPane pane = new BorderPane(); // Initializes a BorderPane as the main layout.
        scrollContainer.setContent(pane); // Embeds the BorderPane inside the ScrollPane.

        LoginPane loginPane = new LoginPane(pane); // Creates a LoginPane instance, passing the BorderPane to it.
        pane.setCenter(loginPane.getPane()); // Sets the LoginPane as the initial content of the BorderPane center.

        Scene scene = new Scene(scrollContainer, 370, 650); // Creates a Scene with the ScrollPane and specific dimensions.
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm()); // Adds external CSS for styling.
        primaryStage.setTitle("Restaurant App"); // Sets the title of the application window.
        primaryStage.setScene(scene); // Assigns the Scene to the Stage.
        primaryStage.show(); // Displays the application window.
    }

    // Static method to create demo customers and add them to the user manager.
    public static void createCustomers() {
        Customer customerDemo1 = new Customer("Customer Demo 1", "customerdemo1@gmail.com", "customer1pass");
        Customer customerDemo2 = new Customer("Customer Demo 2", "customerdemo2@gmail.com", "customer2pass");
        Customer customerDemo3 = new Customer("Customer Demo 3", "customerdemo3@gmail.com", "customer3pass");
        Customer customerDemo4 = new Customer("Customer Demo 4", "customerdemo4@gmail.com", "customer4pass");
        Customer customerDemo5 = new Customer("Customer Demo 5", "customerdemo5@gmail.com", "customer5pass");
        userManager.createUser(customerDemo1); // Adds the first customer to the UserManager.
        userManager.createUser(customerDemo2); // Adds the second customer to the UserManager.
        userManager.createUser(customerDemo3); // Adds the third customer to the UserManager.
        userManager.createUser(customerDemo4); // Adds the fourth customer to the UserManager.
        userManager.createUser(customerDemo5); // Adds the fifth customer to the UserManager.
    }

    // Static method to create demo delivery drivers and add them to the user manager.
    public static void createDrivers() {
        DeliveryDriver driverDemo1 = new DeliveryDriver("Delivery Demo 1", "deliverydemo1@gmail.com", "delivery1pass");
        DeliveryDriver driverDemo2 = new DeliveryDriver("Delivery Demo 2", "deliverydemo2@gmail.com", "delivery2pass");
        DeliveryDriver driverDemo3 = new DeliveryDriver("Delivery Demo 3", "deliverydemo3@gmail.com", "delivery3pass");
        DeliveryDriver driverDemo4 = new DeliveryDriver("Delivery Demo 4", "deliverydemo4@gmail.com", "delivery4pass");
        DeliveryDriver driverDemo5 = new DeliveryDriver("Delivery Demo 5", "deliverydemo5@gmail.com", "delivery5pass");
        userManager.createUser(driverDemo1); // Adds the first delivery driver to the UserManager.
        userManager.createUser(driverDemo2); // Adds the second delivery driver to the UserManager.
        userManager.createUser(driverDemo3); // Adds the third delivery driver to the UserManager.
        userManager.createUser(driverDemo4); // Adds the fourth delivery driver to the UserManager.
        userManager.createUser(driverDemo5); // Adds the fifth delivery driver to the UserManager.
    }

    // Static method to create a restaurant with a fast-food menu.
    public static Restaurant getFastFoodRestaurant() {
        ArrayList<Item> fastFoodMenu = new ArrayList<>(); // Initializes an ArrayList to store menu items.
        Item item1 = new Item("Burger", 9.99, true); // Creates a menu item "Burger".
        Item item2 = new Item("Fries", 3.99, true); // Creates a menu item "Fries".
        Item item3 = new Item("Drink", 2.99, true); // Creates a menu item "Drink".
        Collections.addAll(fastFoodMenu, item1, item2, item3); // Adds the items to the menu list.
        Restaurant fastFood = new Restaurant("Fast Food Joint", "Fast Food", "1", fastFoodMenu); // Creates the restaurant.

        return fastFood; // Returns the created restaurant.
    }

    // Static method to create an "Applebees" restaurant with a menu.
    public static Restaurant getApplebees() {
        ArrayList<Item> applebeesMenu = new ArrayList<>();
        Item item1 = new Item("8 oz Top Sirloin", 18.49, true);
        Item item2 = new Item("Whiskey Bacon Burger", 14.49, true);
        Item item3 = new Item("Neighborhood Burger", 12.49, true);
        Collections.addAll(applebeesMenu, item1, item2, item3);
        Restaurant applebees = new Restaurant("Applebees", "Casual Dining", "2", applebeesMenu);

        return applebees;
    }

    // Static method to create a "Texas Roadhouse" restaurant with a menu.
    public static Restaurant getTexasRoadhouse() {
        ArrayList<Item> texasRoadhouseMenu = new ArrayList<>();
        Item item1 = new Item("Cactus Blossom", 8.99, true);
        Item item2 = new Item("12 oz New York Strip", 24.99, true);
        Item item3 = new Item("Grilled Chicken Salad", 14.49, true);

        Collections.addAll(texasRoadhouseMenu, item1, item2, item3);

        Restaurant texasRoadhouse = new Restaurant("Texas Roadhouse", "Casual Dining", "3", texasRoadhouseMenu);

        return texasRoadhouse;
    }

    // Static method to create orders and assign them to customers and drivers.
    public static void createOrders() {
        Customer customer1 = userManager.getCustomerByEmail("customerdemo1@gmail.com"); // Fetches a customer by email.
        Order order1 = new Order("1", customer1, getFastFoodRestaurant()); // Creates an order for the fast-food restaurant.
        order1.addItems(order1.getRestaurant().getMenu().get(0)); // Adds the first menu item to the order.
        order1.addItems(order1.getRestaurant().getMenu().get(1)); // Adds the second menu item to the order.
        customer1.orders.add(order1); // Adds the order to the customer's list of orders.

        Order order2 = new Order("2", customer1, getTexasRoadhouse()); // Creates another order for a different restaurant.
        order2.addItems(order2.getRestaurant().getMenu().get(0));
        order2.addItems(order2.getRestaurant().getMenu().get(1));
        order2.setStatus("completed"); // Marks the order as completed.
        customer1.orders.add(order2); // Adds this order to the customer's list.

        DeliveryDriver deliveryDriver1 = userManager.getDriverByEmail("deliverydemo1@gmail.com"); // Fetches a driver by email.
        deliveryDriver1.addCompletedOrder(order2); // Adds the completed order to the driver's records.
        deliveryDriver1.addOngoingOrder(order1.getOrderId(), order1); // Assigns the ongoing order to the driver.
    }

  
    public static void main(String args[]) {
        createCustomers(); // Creates demo customers.
        createDrivers(); // Creates demo drivers.
        createOrders(); // Creates demo orders.
        launch(args); 
    }
}
