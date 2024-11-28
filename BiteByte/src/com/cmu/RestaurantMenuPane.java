package com.cmu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class RestaurantMenuPane implements BasePane {

    private BorderPane mainPane;
    private List<Item> cart = new ArrayList<>(); // Cart to store selected items
    private Label cartSummaryLabel; // Label for the cart summary

    public RestaurantMenuPane(BorderPane mainPane) {
        this.mainPane = mainPane;
    }

    @Override
    public Pane getPane() {
        VBox restaurantMenuBox = new VBox(10);
        restaurantMenuBox.setPadding(new Insets(20));

        // Cart Summary Section
        HBox cartSummaryBox = new HBox(10);
        cartSummaryBox.setPadding(new Insets(10));
        cartSummaryBox.setStyle("-fx-border-color: lightgray; -fx-background-color: #f9f9f9; -fx-border-width: 1;");

        Button cartButton = new Button("View Cart");
        cartButton.setOnAction(e -> showCartView());

        cartSummaryLabel = new Label(getCartSummary());
        cartSummaryLabel.setFont(Font.font(14));

        cartSummaryBox.getChildren().addAll(cartButton, cartSummaryLabel);
        restaurantMenuBox.getChildren().add(cartSummaryBox);

        // Restaurant buttons for 5 different restaurants
        Button fastFoodBtn = new Button("Fast Food Joint");
        Button applebeesBtn = new Button("Applebees");
        Button texasRoadhouseBtn = new Button("Texas Roadhouse");
        Button pizzaHutBtn = new Button("Pizza Hut");
        Button subwayBtn = new Button("Subway");

        // Add buttons to the VBox
        restaurantMenuBox.getChildren().addAll(fastFoodBtn, applebeesBtn, texasRoadhouseBtn, pizzaHutBtn, subwayBtn);

        // Set actions for each restaurant button
        fastFoodBtn.setOnAction(e -> showMenu(getFastFoodRestaurant()));
        applebeesBtn.setOnAction(e -> showMenu(getApplebees()));
        texasRoadhouseBtn.setOnAction(e -> showMenu(getTexasRoadhouse()));
        pizzaHutBtn.setOnAction(e -> showMenu(getPizzaHut()));
        subwayBtn.setOnAction(e -> showMenu(getSubway()));

        return restaurantMenuBox;
    }

    private void showMenu(Restaurant restaurant) {
        VBox menuBox = new VBox(10);
        menuBox.setPadding(new Insets(20));

        // Display restaurant name
        Label restaurantLabel = new Label("Restaurant: " + restaurant.getName());
        restaurantLabel.setFont(Font.font(18));

        // ListView to display the restaurant's menu
        ListView<String> menuListView = new ListView<>();
        for (Item item : restaurant.getMenu()) {
            menuListView.getItems().add(item.getName() + " - $" + item.getPrice());
        }

        // Add item to cart button
        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.setOnAction(e -> {
            String selectedItem = menuListView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                for (Item item : restaurant.getMenu()) {
                    if (selectedItem.startsWith(item.getName())) {
                        cart.add(item);
                        updateCartSummary();
                        System.out.println("Added to Cart: " + selectedItem);
                        break;
                    }
                }
            }
        });

        // Back button
        Button backButton = new Button("Back to Restaurants");
        backButton.setOnAction(e -> mainPane.setCenter(getPane()));

        // Layout for the menu view
        menuBox.getChildren().addAll(restaurantLabel, menuListView, addToCartButton, backButton);

        // Switch to the restaurant menu screen
        mainPane.setCenter(menuBox);
    }

    private void showCartView() {
        VBox cartBox = new VBox(10);
        cartBox.setPadding(new Insets(20));
        cartBox.setAlignment(Pos.CENTER);

        // Cart title
        Label cartTitle = new Label("Your Cart");
        cartTitle.setFont(Font.font(18));

        // ListView for cart items
        ListView<String> cartListView = new ListView<>();
        for (Item item : cart) {
            cartListView.getItems().add(item.getName() + " - $" + item.getPrice());
        }

        // Place Order button
        Button placeOrderButton = new Button("Place Your Order");
        placeOrderButton.setOnAction(e -> {
            if (!cart.isEmpty()) {
                // Simulate placing an order
                Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
                confirmationAlert.setTitle("Order Placed");
                confirmationAlert.setHeaderText(null);
                confirmationAlert.setContentText("Your order has been placed successfully!");
                confirmationAlert.showAndWait();

                // Clear the cart after placing the order
                cart.clear();
                updateCartSummary();
                cartListView.getItems().clear();
            } else {
                // Show an error if the cart is empty
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Your cart is empty. Add items to place an order.");
                errorAlert.showAndWait();
            }
        });

        // Back button
        Button backButton = new Button("Back to Restaurants");
        backButton.setOnAction(e -> mainPane.setCenter(getPane()));

        // Add elements to the cart view
        cartBox.getChildren().addAll(cartTitle, cartListView, placeOrderButton, backButton);

        // Display the cart view
        mainPane.setCenter(cartBox);
    }

    private String getCartSummary() {
        int itemCount = cart.size();
        double totalPrice = cart.stream().mapToDouble(Item::getPrice).sum();
        return String.format("%d items - $%.2f", itemCount, totalPrice);
    }

    private void updateCartSummary() {
        cartSummaryLabel.setText(getCartSummary());
    }

    // Methods to generate restaurants and their menus
    private Restaurant getFastFoodRestaurant() {
        ArrayList<Item> fastFoodMenu = new ArrayList<>();
        fastFoodMenu.add(new Item("Burger", 9.99, true));
        fastFoodMenu.add(new Item("Fries", 3.99, true));
        fastFoodMenu.add(new Item("Drink", 2.99, true));
        return new Restaurant("Fast Food Joint", "Fast Food", "1", fastFoodMenu);
    }

    private Restaurant getApplebees() {
        ArrayList<Item> applebeesMenu = new ArrayList<>();
        applebeesMenu.add(new Item("8 oz Top Sirloin", 18.49, true));
        applebeesMenu.add(new Item("Whiskey Bacon Burger", 14.49, true));
        applebeesMenu.add(new Item("Neighborhood Burger", 12.49, true));
        return new Restaurant("Applebees", "Casual Dining", "2", applebeesMenu);
    }

    private Restaurant getTexasRoadhouse() {
        ArrayList<Item> texasRoadhouseMenu = new ArrayList<>();
        texasRoadhouseMenu.add(new Item("Cactus Blossom", 8.99, true));
        texasRoadhouseMenu.add(new Item("12 oz New York Strip", 24.99, true));
        texasRoadhouseMenu.add(new Item("Grilled Chicken Salad", 14.49, true));
        return new Restaurant("Texas Roadhouse", "Casual Dining", "3", texasRoadhouseMenu);
    }

    private Restaurant getPizzaHut() {
        ArrayList<Item> pizzaHutMenu = new ArrayList<>();
        pizzaHutMenu.add(new Item("Large Pizza", 15.99, true));
        pizzaHutMenu.add(new Item("Garlic Bread", 3.99, true));
        pizzaHutMenu.add(new Item("Soda", 2.49, true));
        return new Restaurant("Pizza Hut", "Pizza", "4", pizzaHutMenu);
    }

    private Restaurant getSubway() {
        ArrayList<Item> subwayMenu = new ArrayList<>();
        subwayMenu.add(new Item("Footlong Sub", 6.99, true));
        subwayMenu.add(new Item("Cookies", 1.99, true));
        subwayMenu.add(new Item("Soda", 2.49, true));
        return new Restaurant("Subway", "Sandwiches", "5", subwayMenu);
    }
}
