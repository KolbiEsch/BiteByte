package com.cmu;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ProfilePane {
	
	public Pane getProfilePane() {
		GridPane grid = new GridPane();
		
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
		
		grid.add(line1Lbl, 0, 0);
		grid.add(line1Field, 1, 0);
		
		grid.add(line2Lbl, 0, 1);
		grid.add(line2Field, 1, 1);
		grid.add(cityLbl, 0, 2);
		grid.add(cityField, 1, 2);
		grid.add(stateLbl, 0, 3);
		grid.add(stateField, 1, 3);
		grid.add(ZIPLbl, 0, 4);
		grid.add(ZIPField, 1, 4);
		
		grid.setStyle("-fx-border-color: orange");
		
		return grid;
	}
	
}
