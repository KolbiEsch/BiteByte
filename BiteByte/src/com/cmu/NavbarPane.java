package com.cmu; 

import javafx.geometry.Insets; 
import javafx.scene.control.Button; 
import javafx.scene.layout.BorderPane; 
import javafx.scene.layout.HBox; 
import javafx.scene.layout.Pane; 

// Class to create a navigation bar that integrates different panes into the main application layout.
public class NavbarPane implements BasePane {

    private BorderPane mainPane; // The main pane where different components will be displayed dynamically.
    private ProfilePane profilePane; // A ProfilePane instance for displaying user profile information.
    private RestaurantMenuPane restaurantMenuPane; // A RestaurantMenuPane instance for displaying restaurant menus.

    // Constructor that initializes the NavbarPane and links it to the main application layout.
    public NavbarPane(BorderPane mainPane) {
        this.mainPane = mainPane; // Assigns the main application pane to the local variable.
        this.profilePane = new ProfilePane(); // Initializes the ProfilePane instance.
        this.restaurantMenuPane = new RestaurantMenuPane(mainPane); // Initializes the RestaurantMenuPane instance with the main pane.
    }

    // Method to create and return the navigation bar as a Pane object.
    public Pane getPane() {
        HBox navbar = new HBox(); // Creates an HBox to arrange navigation buttons horizontally.

        Button profileBtn = new Button("Profile"); // Creates a button labeled "Profile".
        navbar.setMargin(profileBtn, new Insets(0, 10, 0, 0)); // Sets the margin around the profile button.
        Button restaurantsBtn = new Button("Restaurants"); // Creates a button labeled "Restaurants".

        navbar.setPadding(new Insets(15)); // Adds padding around the entire navigation bar for better spacing.
        navbar.getChildren().addAll(profileBtn, restaurantsBtn); // Adds both buttons to the navigation bar.

        // Sets the action to be performed when the "Profile" button is clicked.
        profileBtn.setOnAction(e -> mainPane.setCenter(profilePane.getPane()));

        // Sets the action to be performed when the "Restaurants" button is clicked.
        restaurantsBtn.setOnAction(e -> mainPane.setCenter(restaurantMenuPane.getPane()));

        return navbar; 
    }
}
