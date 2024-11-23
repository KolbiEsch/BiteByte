package com.cmu;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

	public void start(Stage primaryStage) {
		BorderPane pane = new BorderPane();
		HBox navbar = new HBox();
		
		Button profileBtn = new Button("Profile");
		navbar.setMargin(profileBtn, new Insets(0, 10, 0, 0));
		Button restaurantsBtn = new Button("Restaurants");
		
		navbar.setPadding(new Insets(15));
		navbar.getChildren().addAll(profileBtn, restaurantsBtn);
		
		pane.setTop(navbar);
		
		profileBtn.setOnAction(e -> {
			ProfilePane profile = new ProfilePane();
			pane.setCenter(profile.getProfilePane());
		});
		
		Scene scene = new Scene(pane, 350, 350);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		UserManager userManager = UserManager.getInstance();
		Customer user1 = new Customer("John Doe", "johndoe@gmail.com", "1234");
		userManager.createUser(user1);
		user1.setSignedIn(true);
		launch(args);
	}
}
