package com.cmu;

// Import necessary JavaFX classes for UI design and functionality
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

// Import necessary Java utility classes for handling data
import java.util.ArrayList;
import java.util.List;

// Class definition for the RestaurantMenuPane, implementing the BasePane interface
public class RestaurantMenuPane implements BasePane {

    private BorderPane mainPane; // Main pane for the application layout
    private List<Item> cart = new ArrayList<>(); // List to store selected items in the cart
    private Label cartSummaryLabel; // Label to display the summary of items in the cart

    // Constructor to initialize the main pane
    public RestaurantMenuPane(BorderPane mainPane) {
        this.mainPane = mainPane;
    }

    @Override
    public Pane getPane() {
        VBox restaurantMenuBox = new VBox(10); // Vertical box layout for restaurant menu options
        restaurantMenuBox.setPadding(new Insets(20)); // Set padding around the layout

        // Section for displaying the cart summary
        HBox cartSummaryBox = new HBox(10); // Horizontal box layout for cart summary
        cartSummaryBox.setPadding(new Insets(10)); // Set padding around the cart summary section
        cartSummaryBox.setStyle("-fx-border-color: lightgray; -fx-background-color: #f9f9f9; -fx-border-width: 1;"); // Add styling to the cart summary section

        Button cartButton = new Button("View Cart"); // Button to view the cart
        cartButton.setOnAction(e -> showCartView()); // Set an action to display the cart view when clicked

        cartSummaryLabel = new Label(getCartSummary()); // Create a label displaying the current cart summary
        cartSummaryLabel.setFont(Font.font(14)); // Set the font size for the cart summary label

        // Add the cart button and summary label to the cart summary section
        cartSummaryBox.getChildren().addAll(cartButton, cartSummaryLabel);
        restaurantMenuBox.getChildren().add(cartSummaryBox); // Add the cart summary section to the menu box

        // Buttons for selecting different restaurants
        Button fastFoodBtn = new Button("Fast Food Joint");
        Button applebeesBtn = new Button("Applebees");
        Button texasRoadhouseBtn = new Button("Texas Roadhouse");
        Button pizzaHutBtn = new Button("Pizza Hut");
        Button subwayBtn = new Button("Subway");

        // Add the restaurant buttons to the menu layout
        restaurantMenuBox.getChildren().addAll(fastFoodBtn, applebeesBtn, texasRoadhouseBtn, pizzaHutBtn, subwayBtn);

        // Define actions for each restaurant button to display the corresponding menu
        fastFoodBtn.setOnAction(e -> showMenu(getFastFoodRestaurant()));
        applebeesBtn.setOnAction(e -> showMenu(getApplebees()));
        texasRoadhouseBtn.setOnAction(e -> showMenu(getTexasRoadhouse()));
        pizzaHutBtn.setOnAction(e -> showMenu(getPizzaHut()));
        subwayBtn.setOnAction(e -> showMenu(getSubway()));

        return restaurantMenuBox; // Return the menu layout
    }

    // Method to display the menu for a selected restaurant
    private void showMenu(Restaurant restaurant) {
        VBox menuBox = new VBox(10); // Vertical box layout for the restaurant menu
        menuBox.setPadding(new Insets(20)); // Set padding around the layout

        Label restaurantLabel = new Label("Restaurant: " + restaurant.getName()); // Label displaying the restaurant name
        restaurantLabel.setFont(Font.font(18)); // Set font size for the restaurant label

        ListView<String> menuListView = new ListView<>(); // ListView to display the restaurant's menu items
        for (Item item : restaurant.getMenu()) {
            menuListView.getItems().add(item.getName() + " - $" + item.getPrice()); // Add each menu item to the ListView
        }

        Button addToCartButton = new Button("Add to Cart"); // Button to add selected menu item to the cart
        addToCartButton.setOnAction(e -> {
            String selectedItem = menuListView.getSelectionModel().getSelectedItem(); // Get the selected item from the menu
            if (selectedItem != null) {
                for (Item item : restaurant.getMenu()) {
                    if (selectedItem.startsWith(item.getName())) { // Match the selected item with menu items
                        cart.add(item); // Add the item to the cart
                        updateCartSummary(); // Update the cart summary
                        System.out.println("Added to Cart: " + selectedItem); // Log the added item
                        break;
                    }
                }
            }
        });

        Button backButton = new Button("Back to Restaurants"); // Button to navigate back to the restaurant menu
        backButton.setOnAction(e -> mainPane.setCenter(getPane())); // Set action to display the restaurant menu layout

        // Add all elements to the menu layout
        menuBox.getChildren().addAll(restaurantLabel, menuListView, addToCartButton, backButton);

        mainPane.setCenter(menuBox); // Display the menu layout in the main pane
    }

    // Method to display the cart view
    private void showCartView() {
        VBox cartBox = new VBox(10); // Vertical box layout for the cart view
        cartBox.setPadding(new Insets(20)); // Set padding around the layout
        cartBox.setAlignment(Pos.CENTER); // Center align the cart view elements

        Label cartTitle = new Label("Your Cart"); // Label for the cart title
        cartTitle.setFont(Font.font(18)); // Set font size for the cart title

        ListView<String> cartListView = new ListView<>(); // ListView to display items in the cart
        for (Item item : cart) {
            cartListView.getItems().add(item.getName() + " - $" + item.getPrice()); // Add each cart item to the ListView
        }

        Button placeOrderButton = new Button("Place Your Order"); // Button to place the order
        placeOrderButton.setOnAction(e -> {
            if (!cart.isEmpty()) {
                Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION); // Create an alert for order confirmation
                confirmationAlert.setTitle("Order Placed"); // Set the title of the alert
                confirmationAlert.setHeaderText(null); // Remove the header text
                confirmationAlert.setContentText("Your order has been placed successfully!"); // Set the alert content text
                confirmationAlert.showAndWait(); // Show the alert

                cart.clear(); // Clear the cart after placing the order
                updateCartSummary(); // Update the cart summary
                cartListView.getItems().clear(); // Clear the ListView
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR); // Create an alert for an empty cart error
                errorAlert.setTitle("Error"); // Set the title of the alert
                errorAlert.setHeaderText(null); // Remove the header text
                errorAlert.setContentText("Your cart is empty. Add items to place an order."); // Set the alert content text
                errorAlert.showAndWait(); // Show the alert
            }
        });

        Button backButton = new Button("Back to Restaurants"); // Button to navigate back to the restaurant menu
        backButton.setOnAction(e -> mainPane.setCenter(getPane())); // Set action to display the restaurant menu layout

        cartBox.getChildren().addAll(cartTitle, cartListView, placeOrderButton, backButton); // Add all elements to the cart layout
        mainPane.setCenter(cartBox); // Display the cart layout in the main pane
    }

    private String getCartSummary() {
        int itemCount = cart.size(); // Get the number of items in the cart
        double totalPrice = cart.stream().mapToDouble(Item::getPrice).sum(); // Calculate the total price of items in the cart
        return String.format("%d items - $%.2f", itemCount, totalPrice); // Return a formatted cart summary string
    }

    private void updateCartSummary() {
        cartSummaryLabel.setText(getCartSummary()); // Update the text of the cart summary label
    }

    // Methods to generate sample restaurant data and menus
    private Restaurant getFastFoodRestaurant() { // Generate a fast food restaurant and its menu
        ArrayList<Item> fastFoodMenu = new ArrayList<>();
        fastFoodMenu.add(new Item("Burger", 9.99, true));
        fastFoodMenu.add(new Item("Fries", 3.99, true));
        fastFoodMenu.add(new Item("Drink", 2.99, true));
        return new Restaurant("Fast Food Joint", "Fast Food", "1", fastFoodMenu);
    }

    private Restaurant getApplebees() { // Generate an Applebee's restaurant and its menu
        ArrayList<Item> applebeesMenu = new ArrayList<>();
        applebeesMenu.add(new Item("8 oz Top Sirloin", 18.49, true));
        applebeesMenu.add(new Item("Whiskey Bacon Burger", 14.49, true));
        applebeesMenu.add(new Item("Neighborhood Burger", 12.49, true));
        return new Restaurant("Applebees", "Casual Dining", "2", applebeesMenu);
    }

    private Restaurant getTexasRoadhouse() { // Generate a Texas Roadhouse restaurant and its menu
        ArrayList<Item> texasRoadhouseMenu = new ArrayList<>();
        texasRoadhouseMenu.add(new Item("Cactus Blossom", 8.99, true));
        texasRoadhouseMenu.add(new Item("Fried Pickles", 5.99, true));
        texasRoadhouseMenu.add(new Item("Tater Skins", 6.99, true));
        return new Restaurant("Texas Roadhouse", "Casual Dining", "3", texasRoadhouseMenu);
    }

    private Restaurant getPizzaHut() { // Generate a Pizza Hut restaurant and its menu
        ArrayList<Item> pizzaHutMenu = new ArrayList<>();
        pizzaHutMenu.add(new Item("Pepperoni Pizza", 15.99, true));
        pizzaHutMenu.add(new Item("Supreme Pizza", 17.99, true));
        pizzaHutMenu.add(new Item("Cheese Pizza", 13.99, true));
        return new Restaurant("Pizza Hut", "Fast Food", "4", pizzaHutMenu);
    }

    private Restaurant getSubway() { // Generate a Subway restaurant and its menu
        ArrayList<Item> subwayMenu = new ArrayList<>();
        subwayMenu.add(new Item("Turkey Sub", 8.49, true));
        subwayMenu.add(new Item("Veggie Sub", 7.49, true));
        subwayMenu.add(new Item("Meatball Sub", 9.49, true));
        return new Restaurant("Subway", "Fast Food", "5", subwayMenu);
    }
}
