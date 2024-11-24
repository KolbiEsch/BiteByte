package com.cmu;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class NavbarPane implements BasePane {

	BorderPane mainPane;
	private ProfilePane profilePane;
	
	public NavbarPane(BorderPane mainPane) {
		this.mainPane = mainPane;
		this.profilePane = new ProfilePane();
	}
	
	public Pane getPane() {
		HBox navbar = new HBox();
		
		Button profileBtn = new Button("Profile");
		navbar.setMargin(profileBtn, new Insets(0, 10, 0, 0));
		Button restaurantsBtn = new Button("Restaurants");
		
		navbar.setPadding(new Insets(15));
		navbar.getChildren().addAll(profileBtn, restaurantsBtn);
		
		// Example of BorderPane nav relationship.
		profileBtn.setOnAction(e -> {
			mainPane.setCenter(profilePane.getPane());
		});
		
		return navbar;
	}
	
}
