package com.cmu;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class NavbarPane implements BasePane {

    private BorderPane mainPane;
    private ProfilePane profilePane;
    private RestaurantMenuPane restaurantMenuPane;

    public NavbarPane(BorderPane mainPane) {
        this.mainPane = mainPane;
        this.profilePane = new ProfilePane();
        this.restaurantMenuPane = new RestaurantMenuPane(mainPane); // Initialize RestaurantMenuPane
    }

    public Pane getPane() {
        HBox navbar = new HBox();

        Button profileBtn = new Button("Profile");
        navbar.setMargin(profileBtn, new Insets(0, 10, 0, 0));
        Button restaurantsBtn = new Button("Restaurants");

        navbar.setPadding(new Insets(15));
        navbar.getChildren().addAll(profileBtn, restaurantsBtn);

        // Set action for profileBtn
        profileBtn.setOnAction(e -> mainPane.setCenter(profilePane.getPane()));

        // Set action for restaurantsBtn
        restaurantsBtn.setOnAction(e -> mainPane.setCenter(restaurantMenuPane.getPane()));

        return navbar;
    }
}

