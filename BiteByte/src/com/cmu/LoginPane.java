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
		loginPane.setPadding(new Insets(20));
		loginPane.setHgap(10);
		loginPane.setVgap(10);

		// Labels
		Label emailLbl = new Label("Email");
		Label passwordLbl = new Label("Password");
		Label loginErrorLbl = new Label("Incorrect username or password");
		loginErrorLbl.setStyle("-fx-text-fill: red;");
		loginErrorLbl.setVisible(false);

		// Fields
		TextField emailField = new TextField();
		PasswordField passwordField = new PasswordField();

		// Buttons
		Button loginBtn = new Button("Login");
		Button createAccountBtn = new Button("Create an Account");

		// Add elements to the grid
		loginPane.add(emailLbl, 0, 0);
		loginPane.add(emailField, 1, 0);

		loginPane.add(passwordLbl, 0, 1);
		loginPane.add(passwordField, 1, 1);

		loginPane.add(loginBtn, 1, 2);
		loginPane.add(createAccountBtn, 1, 3);

		// Login button action
		loginBtn.setOnAction(e -> {
			User user = SignInManager.signIn(emailField.getText(), passwordField.getText());
			if (user == null) {
				loginErrorLbl.setVisible(true);
				if (!loginPane.getChildren().contains(loginErrorLbl)) {
					loginPane.add(loginErrorLbl, 1, 4);
				}
			} else {
				loginErrorLbl.setVisible(false);
				if (user instanceof DeliveryDriver) {
					DeliveryDriverPane driverPane = new DeliveryDriverPane((DeliveryDriver) user);
					mainPane.setCenter(driverPane.getPane());
				} else {
					mainPane.setTop(getNavbar(mainPane));
					mainPane.setCenter(getCustomerHomePane());
					user.setSignedIn(true);
				}
			}
		});

		// Create account button action
		createAccountBtn.setOnAction(e -> {
			mainPane.setCenter(new CreateAccountPane(mainPane).getPane());
		});

		return loginPane;
	}

	private Pane getCustomerHomePane() {
		return new HomePane().getPane();
	}

	private Pane getNavbar(BorderPane mainPane) {
		return new NavbarPane(mainPane).getPane();
	}
}
