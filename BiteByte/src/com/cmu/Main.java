package com.cmu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

	public void start(Stage primaryStage) {
		BorderPane pane = new BorderPane();
		HBox navbar = new HBox();
		
		Button profileBtn = new Button("Profile");
		Button restaurantsBtn = new Button("Restaurants");
		
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
		launch(args);
	}
}
