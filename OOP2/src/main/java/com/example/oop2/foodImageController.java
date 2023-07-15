package com.example.oop2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class foodImageController
{
    @FXML
    private Label label;
    @FXML
    private ImageView image;
    public foodImageController(String name,String imgURL)
    {
        image.setImage(new Image(imgURL));
        label.setText(name);
    }
    public void setFoodImageController(String name,String imgURL)
    {
        image.setImage(new Image(imgURL));
        label.setText(name);
    }



}
