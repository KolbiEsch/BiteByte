package com.group;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

// Basic but should work for now. Will change where needed

public class Restaurant {
    String name, type, ID;
    ArrayList<Item> menu = new ArrayList<>();

    Restaurant(String name, String type, String ID, ArrayList<Item> menu){
        this.name = name;
        this.type = type;
        this.ID = ID;
        this.menu = menu;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public String getID(){
        return ID;
    }

    public ArrayList<Item> getMenu(){
        return menu;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setID(String ID){
        this.ID = ID;
    }

    public void setMenu(ArrayList<Item> menu){
        this.menu = menu;
    }
}