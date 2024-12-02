package com.cmu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public class ProfilePane implements BasePane {
	
	private Button accountBtn;
	private Button ordersBtn;
	private UserManager userManager;
	
	public ProfilePane() {
		userManager = UserManager.getInstance();
	}
	
	public Pane getPane() {
		VBox profilePane = new VBox();
		profilePane.getStyleClass().add("profile-pane");
		
		Label accountLbl = new Label("Account");
		Label orderLbl = new Label("Orders");
		Pane accountPane = getAccountPane();
		Pane ordersPane = getUserOrdersPane();
		
		profilePane.getChildren().addAll(accountLbl, accountPane, orderLbl, ordersPane);
		profilePane.setMargin(accountLbl, new Insets(0, 0, 5, 0));
		profilePane.setMargin(orderLbl, new Insets(20, 0, 10, 0));
		
		return profilePane;
	}
	
	private Pane getAccountPane() {
		GridPane accountGrid = new GridPane();
		
		User user = userManager.getCurrentUser();
		
		Label emailLbl = new Label("Email");
		Label passwordLbl = new Label("Password");
		
		TextField emailField = new TextField();
		emailField.setEditable(false);
		emailField.setText(user.getEmail());
		PasswordField passwordField = new PasswordField();
		passwordField.setText(user.getPassword());
		
		Button passwordUpdateBtn = new Button("Update Password");
		passwordUpdateBtn.setOnAction(e -> {
			userManager.setUserPasswordAsync(passwordField.getText()).start();
		});
		
		VBox emailPasswordBox = new VBox();
		emailPasswordBox.getChildren().addAll(emailLbl, emailField, passwordLbl, passwordField, passwordUpdateBtn);
		emailPasswordBox.setMargin(passwordUpdateBtn, new Insets(15, 0, 15, 0));
		
		Label addressLbl = new Label("Address");
		Label line1Lbl = new Label("Line 1");
		Label line2Lbl = new Label("Line 2");
		Label cityLbl = new Label("City");
		Label stateLbl = new Label("State");
		Label ZIPLbl = new Label("ZIP Code");
		Label addressUpdateLabel = new Label("Required Fields: line1, city, state,\nZIP");
		
		TextField line1Field = new TextField();
		TextField line2Field = new TextField();
		TextField cityField = new TextField();
		TextField stateField = new TextField();
		TextField ZIPField = new TextField();
		
		VBox addressBox = new VBox();
		
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
				if (!addressBox.getChildren().contains(addressUpdateLabel)) {
					accountGrid.add(addressUpdateLabel, 0, 9, 2, 1);
				}
			}
		});
		
		addressBox.getChildren().addAll(addressLbl, line1Lbl, line1Field, line2Lbl, line2Field, cityLbl, cityField,
				stateLbl, stateField, ZIPLbl, ZIPField, addressUpdateBtn);
		addressBox.setMargin(addressUpdateBtn, new Insets(15, 0, 0, 0));
		
		accountGrid.add(emailPasswordBox, 0, 0);
		accountGrid.add(addressBox, 0, 1);
	
		return accountGrid;
	}
	
	private Pane getUserOrdersPane() {
		VBox ordersPane = new VBox();
		
		Customer currentCustomer = userManager.getCurrentCustomer();
		TableView<Order> ordersView = new TableView<>();
		
		// Dynamically resize table columns on window resize.
		ordersView.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double tableWidth = newWidth.doubleValue();
            double columnWidth = tableWidth / ordersView.getColumns().size();
            for (TableColumn<Order, ?> column : ordersView.getColumns()) {
                column.setPrefWidth(columnWidth);
            }
        });
		
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
