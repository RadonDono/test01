package com.example.oop2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class randomController
{
    @FXML
    private Button button;
    @FXML
    private BorderPane borderPane;

    public void setButton(ActionEvent event) throws IOException
    {
        Pane pane=FXMLLoader.

                load(getClass().getResource("foodImage.fxml"));
        borderPane.setCenter(pane);
    }


}
