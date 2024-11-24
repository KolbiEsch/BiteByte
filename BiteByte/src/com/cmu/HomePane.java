package com.cmu;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class HomePane implements BasePane {

	public Pane getPane() {
		return new GridPane();
	}
	
}
