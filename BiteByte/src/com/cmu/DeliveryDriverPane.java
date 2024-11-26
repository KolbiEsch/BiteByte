package com.cmu;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class DeliveryDriverPane implements BasePane{

	private DeliveryDriver driver;
	
	public DeliveryDriverPane(DeliveryDriver driver) {
		this.driver = driver;
	}
	
	@Override
	public Pane getPane()
	{
		BorderPane borderPane = new BorderPane();
		
		Label driverName = new Label("Driver: " + driver.getName());
		HBox driverInfo = new HBox();
		driverInfo.setAlignment(Pos.TOP_CENTER);
		driverInfo.getChildren().add(driverName);
		
		VBox ordersBox = new VBox(10);
		ordersBox.getChildren().addAll(getOngoingOrders(), getCompletedOrders());
		ordersBox.setAlignment(Pos.CENTER);
		
		borderPane.setCenter(ordersBox);
		borderPane.setTop(driverInfo);
		
	
		return borderPane;
	}
	
	public Pane getOngoingOrders()
	{
		
		ListView<String> ongoingOrders = new ListView<>();	
//		ongoingOrders.setCellFactory(e -> new ListCell<String>{
//	        @Override
//	        protected void updateItem(String item, boolean empty) {
//	            super.updateItem(item, empty);
//	            if (!empty && item != null) { // Ensure that the item is not null and the cell is not empty
//	                setText(item);
//	                setFont(Font.font(12)); // Setting the font size to 12
//	            } else {
//	                setText(null); // Clear the text to ensure cells are properly cleared
//	            }
//	        }
//	    });

				
		
		
		Button btnOngoing = new Button("Refresh Current Orders");
		btnOngoing.setMaxSize(275, 30);
		btnOngoing.setOnAction(e -> 
		{
			ongoingOrders.getItems().clear();
			
		    // Populate the ListView with ongoing orders
		    for (String orderId : driver.ongoingOrders.keySet()) 
		    {
		        Order order = driver.ongoingOrders.get(orderId);
		        ongoingOrders.getItems().add("Order ID: " + orderId + "Customer: " + order.getCustomer().getName() + " Status:" + order.getStatus());
		    }
		});
		
		
		
		VBox vBox = new VBox();
		vBox.setMaxWidth(275);
		vBox.setMaxHeight(150);
		vBox.getChildren().addAll(btnOngoing, ongoingOrders);
		
		
		return vBox;
	
	}
	
	
	public Pane getCompletedOrders()
	{
		ListView<String> completedOrders = new ListView<>();
		
		Button btnCompleted = new Button("Refresh Completed Orders");
		btnCompleted.setMaxSize(275, 30);
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
		vBox.setMaxWidth(275);
		vBox.setMaxHeight(150);
		vBox.getChildren().addAll(btnCompleted, completedOrders);
		
		return vBox;
		
	}
	
	// public Pane getDeliveryInformation(){
		
	// 	Label customerDetails = new Label();
		
	// }


	

	
	
	

}
