package com.example.oop2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        Parent root=FXMLLoader.load(getClass().getResource("welcomeMenu.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("SnapFood");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}