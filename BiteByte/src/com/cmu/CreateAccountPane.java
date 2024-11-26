package com.cmu;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class CreateAccountPane {

    private BorderPane mainPane;

    public CreateAccountPane(BorderPane mainPane) {
        this.mainPane = mainPane;
    }

    public Pane getPane() {
        // Create a grid layout
        GridPane createAccountPane = new GridPane();
        createAccountPane.setPadding(new Insets(20));
        createAccountPane.setHgap(10);
        createAccountPane.setVgap(10);

        // Title Label
        Label titleLabel = new Label("Create an Account");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        // Input Labels and Fields
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        // Error Label
        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");

        // Buttons
        Button createAccountBtn = new Button("Create Account");
        Button backBtn = new Button("Back");

        // Add elements to the grid
        createAccountPane.add(titleLabel, 0, 0, 2, 1);
        createAccountPane.add(nameLabel, 0, 1);
        createAccountPane.add(nameField, 1, 1);
        createAccountPane.add(emailLabel, 0, 2);
        createAccountPane.add(emailField, 1, 2);
        createAccountPane.add(passwordLabel, 0, 3);
        createAccountPane.add(passwordField, 1, 3);
        createAccountPane.add(errorLabel, 0, 4, 2, 1);
        createAccountPane.add(createAccountBtn, 0, 5);
        createAccountPane.add(backBtn, 1, 5);

        // Create Account Button Action
        createAccountBtn.setOnAction(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                errorLabel.setText("All fields are required.");
            } else if (UserManager.getInstance().getUsers().stream()
                    .anyMatch(user -> user.getEmail().equals(email))) {
                errorLabel.setText("Email is already in use.");
            } else {
                // Create the new user
                UserManager.getInstance().createUser(new Customer(name, email, password));
                // Redirect back to LoginPane
                mainPane.setCenter(new LoginPane(mainPane).getPane());
            }
        });

        // Back Button Action
        backBtn.setOnAction(e -> mainPane.setCenter(new LoginPane(mainPane).getPane()));

        return createAccountPane;
    }
}
