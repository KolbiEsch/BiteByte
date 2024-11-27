package com.cmu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ProfilePane implements BasePane {
	
	private Button accountBtn;
	private Button ordersBtn;
	private UserManager userManager;
	
	public ProfilePane() {
		userManager = UserManager.getInstance();
	}
	
	public Pane getPane() {
		BorderPane pane = new BorderPane();
		
		Pane profileNav = getNav();
		Pane accountPane = getAccountPane();
		Pane ordersPane = getUserOrdersPane();
		
		pane.setLeft(profileNav);
		pane.setCenter(accountPane);
		
		
		
		accountBtn.setOnAction(e -> {
			pane.setCenter(accountPane);
		});
		
		ordersBtn.setOnAction(e -> {
			pane.setCenter(ordersPane);
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
		emailField.setEditable(false);
		emailField.setText(user.getEmail());
		PasswordField passwordField = new PasswordField();
		passwordField.setText(user.getPassword());
		
		Label line1Lbl = new Label("Line 1:");
		Label line2Lbl = new Label("Line 2:");
		Label cityLbl = new Label("City:");
		Label stateLbl = new Label("State:");
		Label ZIPLbl = new Label("ZIP Code:");
		Label addressUpdateLabel = new Label("Required Fields: line1, city, state, ZIP");
		
		TextField line1Field = new TextField();
		TextField line2Field = new TextField();
		TextField cityField = new TextField();
		TextField stateField = new TextField();
		TextField ZIPField = new TextField();
		
		Button passwordUpdateBtn = new Button("Update Password");
		passwordUpdateBtn.setOnAction(e -> {
			userManager.setUserPasswordAsync(passwordField.getText()).start();
		});
		
		Button addressUpdateBtn = new Button("Update Address");
		addressUpdateBtn.setOnAction(e -> {
			String line1, line2, city, state, ZIP;
			line1 = line1Field.getText();
			line2 = line1Field.getText();
			city = cityField.getText();
			state = stateField.getText();
			ZIP = ZIPField.getText();
			
			Address address;
			
			if (line1.length() != 0 || city.length() != 0 || state.length() != 0 || ZIP.length() != 0) {
				if (line2.length() != 0) {
					address = new Address(line1, line2, city, state, ZIP);
				} else {
					address = new Address(line1, city, state, ZIP);
				}
				userManager.setCustomerAddressAsync(address).start();
			} else {
				if (!accountGrid.getChildren().contains(addressUpdateLabel)) {
					accountGrid.add(addressUpdateLabel, 0, 9);
				}
			}
		});
		
		accountGrid.add(emailLbl, 0, 0);
		accountGrid.add(emailField, 1, 0);
		accountGrid.add(passwordLbl, 0, 1);
		accountGrid.add(passwordField, 1, 1);
		accountGrid.add(passwordUpdateBtn, 0, 2);
		accountGrid.add(line1Lbl, 0, 3);
		accountGrid.add(line1Field, 1, 3);
		accountGrid.add(line2Lbl, 0, 4);
		accountGrid.add(line2Field, 1, 4);
		accountGrid.add(cityLbl, 0, 5);
		accountGrid.add(cityField, 1, 5);
		accountGrid.add(stateLbl, 0, 6);
		accountGrid.add(stateField, 1, 6);
		accountGrid.add(ZIPLbl, 0, 7);
		accountGrid.add(ZIPField, 1, 7);
		accountGrid.add(addressUpdateBtn, 0, 8);
		
		return accountGrid;
	}
	
	private Pane getUserOrdersPane() {
		VBox ordersPane = new VBox();
		
		Customer currentCustomer = userManager.getCurrentCustomer();
		TableView<Order> ordersView = new TableView<>();
		
		TableColumn<Order, String> IDColumn = new TableColumn<>("Order ID");
		IDColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));
		
		TableColumn<Order, String> restaurantNameCol = new TableColumn<>("Restaurant");
		restaurantNameCol.setCellValueFactory(new PropertyValueFactory<>("restaurantName"));
		
		TableColumn<Order, String> orderStatusCol = new TableColumn<>("Status");
		orderStatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
		
		TableColumn<Order, String> priceColumn = new TableColumn<>("Total");
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
		
		ordersView.getColumns().addAll(IDColumn, restaurantNameCol, orderStatusCol, priceColumn);
		
		ObservableList<Order> orders = FXCollections.observableList(currentCustomer.orders);
		
		ordersView.setItems(orders);
		
		ordersPane.getChildren().add(ordersView);
		
		return ordersPane;
	}
}
