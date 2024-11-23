package com.cmu;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ProfilePane {
	
	private Button accountBtn;
	private Button ordersBtn;
	private UserManager userManager;
	
	public ProfilePane() {
		userManager = UserManager.getInstance();
	}
	
	public Pane getProfilePane() {
		BorderPane pane = new BorderPane();
		
		Pane profileNav = getNav();
		Pane accountPane = getAccountPane();
		
		pane.setLeft(profileNav);
		pane.setCenter(accountPane);
		
		GridPane ordersGrid = new GridPane();
		
		accountBtn.setOnAction(e -> {
			pane.setCenter(accountPane);
		});
		
		ordersBtn.setOnAction(e -> {
			pane.setCenter(ordersGrid);
		});
		
		return pane;
	}
	
	private Pane getNav() {
		VBox profileNav = new VBox();
		
		accountBtn = new Button("Account");
		ordersBtn = new Button("Orders");
		
		profileNav.getChildren().addAll(accountBtn, ordersBtn);
		profileNav.setPadding(new Insets(15));
		profileNav.setMargin(ordersBtn, new Insets(10, 0, 0, 0));
		
		return profileNav;
	}
	
	private Pane getAccountPane() {
		GridPane accountGrid = new GridPane();
		
		User user = userManager.getCurrentUser();
		
		Label emailLbl = new Label("Email:");
		Label passwordLbl = new Label("Password:");
		
		TextField emailField = new TextField();
		emailField.setText(user.getEmail());
		PasswordField passwordField = new PasswordField();
		passwordField.setText(user.getPassword());
		
		Label line1Lbl = new Label("Line 1:");
		Label line2Lbl = new Label("Line 2:");
		Label cityLbl = new Label("City:");
		Label stateLbl = new Label("State:");
		Label ZIPLbl = new Label("ZIP Code:");
		
		TextField line1Field = new TextField();
		TextField line2Field = new TextField();
		TextField cityField = new TextField();
		TextField stateField = new TextField();
		TextField ZIPField = new TextField();
		
		Button saveBtn = new Button("Save Changes");
		
		accountGrid.add(emailLbl, 0, 0);
		accountGrid.add(emailField, 1, 0);
		accountGrid.add(passwordLbl, 0, 1);
		accountGrid.add(passwordField, 1, 1);
		accountGrid.add(line1Lbl, 0, 2);
		accountGrid.add(line1Field, 1, 2);
		accountGrid.add(line2Lbl, 0, 3);
		accountGrid.add(line2Field, 1, 3);
		accountGrid.add(cityLbl, 0, 4);
		accountGrid.add(cityField, 1, 4);
		accountGrid.add(stateLbl, 0, 5);
		accountGrid.add(stateField, 1, 5);
		accountGrid.add(ZIPLbl, 0, 6);
		accountGrid.add(ZIPField, 1, 6);
		accountGrid.add(saveBtn, 0, 7);
		
		accountGrid.setStyle("-fx-border-color: orange");
		
		return accountGrid;
	}
}
