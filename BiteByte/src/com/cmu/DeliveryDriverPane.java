package com.cmu;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class DeliveryDriverPane implements BasePane{

	private DeliveryDriver driver;
	
	public DeliveryDriverPane(DeliveryDriver driver) {
		this.driver = driver;
	}
	
	@Override
	public Pane getPane()
	{
		BorderPane borderPane = new BorderPane();
		
		Label driverInformation = new Label("Driver: " + driver.getName() + " Email: " + driver.getEmail());
		
		VBox ordersBox = new VBox(10);
		ordersBox.getChildren().addAll(getOngoingOrders(), getCompletedOrders());
		
		borderPane.setCenter(ordersBox);
		borderPane.setTop(driverInformation);
		
	
		return borderPane;
	}
	
	public Pane getOngoingOrders()
	{
		
		ListView<String> ongoingOrders = new ListView<>();		
		
		Button btnOngoing = new Button("Refresh Current Orders");
		btnOngoing.setOnAction(e -> 
		{
			ongoingOrders.getItems().clear();
			
		    // Populate the ListView with ongoing orders
		    for (String orderId : driver.ongoingOrders.keySet()) 
		    {
		        Order order = driver.ongoingOrders.get(orderId);
		        ongoingOrders.getItems().add(orderId + ": " + order.getStatus());
		    }
		});
		
		
		VBox vBox = new VBox();
		vBox.getChildren().addAll(btnOngoing, ongoingOrders);
		
		return vBox;
	
	}
	
	
	public Pane getCompletedOrders()
	{
		ListView<String> completedOrders = new ListView<>();
		
		Button btnCompleted = new Button("Refresh Completed Orders");
		btnCompleted.setOnAction(e -> 
		{
			completedOrders.getItems().clear();
			
		    // Populate the ListView with ongoing orders
		    for (int i = 0; i < driver.completedOrders.size(); i++) 
		    {
		        Order order = driver.completedOrders.get(i);
		        completedOrders.getItems().add(order.getOrderId() + ": " + order.getStatus());
		    }
		});
		
		VBox vBox = new VBox();
		vBox.getChildren().addAll(btnCompleted, completedOrders);
		
		return vBox;
		
	}


	

	
	
	

}
