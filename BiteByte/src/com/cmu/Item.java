package com.cmu;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

// Basic but should work for now. Will change where needed

public class Item {

    String name;
    double price;
    boolean isAvailable;

    Item(String name, double price, boolean isAvailable){
        this.name = name;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    public boolean getIsAvailable(){
        return isAvailable;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setIsAvailable(boolean isAvaialble){
        this.isAvailable = isAvailable;
    }
}