package com.cmu;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class LoginPane implements BasePane {
	
	private BorderPane mainPane;
	
	public LoginPane(BorderPane mainPane) {
		this.mainPane = mainPane;
	}
	
	public Pane getPane() {
		GridPane loginPane = new GridPane();
		
		Label emailLbl = new Label("Email");
		Label passwordLbl = new Label("Password");
		Label loginErrorLbl = new Label("Incorrect username or password");
		
		TextField emailField = new TextField();
		PasswordField passwordField = new PasswordField();
		
		Button loginBtn = new Button("Login");
		
		loginPane.add(emailLbl, 0, 0);
		loginPane.add(emailField, 0, 1);
		
		loginPane.add(passwordLbl, 0, 2);
		loginPane.add(passwordField, 0, 3);
		loginPane.add(loginBtn, 0, 4);
		
		loginBtn.setOnAction(e -> {
			User user = SignInManager.signIn(emailField.getText(), passwordField.getText());
			if (user == null) {
				loginPane.getChildren().remove(loginErrorLbl);
				loginPane.add(loginErrorLbl, 0, 5);
			} else {
				if (user instanceof DeliveryDriver) {
					mainPane.setCenter(getDriverPane());
				} else {
					mainPane.setTop(getNavbar(mainPane));
					mainPane.setCenter(getCustomerHomePane());
					user.setSignedIn(true);
				}
			}
		});
		
		loginPane.setMargin(passwordLbl, new Insets(8, 0, 0, 0));
		loginPane.setMargin(loginBtn, new Insets(8, 0, 0, 0));
		
		return loginPane;
	}
	
	private Pane getCustomerHomePane() {
		return new HomePane().getPane();
	}
	
	private Pane getNavbar(BorderPane mainPane) {
		return new NavbarPane(mainPane).getPane();
	}
	
	private Pane getDriverPane() {
		return new DeliveryDriverPane().getPane();
	}
}
