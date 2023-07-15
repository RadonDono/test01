package com.example.oop2;

import com.example.oop2.Persons.RestaurantOwner;
import com.example.oop2.controller.RestaurantOwnerController;
import com.example.oop2.controller.WelcomeController;
import com.example.oop2.enums.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Addrestaurancontroller {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    TextField nametext;
    @FXML
    TextField foodtypetext;
    @FXML
    TextField loctext;
    @FXML
    Button addbutton;
    @FXML
    Button exitbutton;
    @FXML
    public Exception showadminMenu(ActionEvent event, RestaurantOwner r) throws IOException {
        RestaurantOwnerController.using=r;
        root= FXMLLoader.load(getClass().getResource("adminMenu.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();

        return null;
    }

    public void add(ActionEvent event) throws IOException {
        String name=nametext.getText();
        String type=foodtypetext.getText();
        String loc=loctext.getText();
        if (name!=null&&type!=null&&loctext!=null) {
            WelcomeController welcomeController=new WelcomeController();
            welcomeController.handleCreateRestaurant(name, RestaurantOwnerController.using.getID(),type,loc);
            showadminMenu(event,RestaurantOwnerController.using);
        }else {
            Alert al=new Alert(Alert.AlertType.CONFIRMATION);
            al.setTitle("Eror!");
            al.setContentText("please enter every information");
            Optional<ButtonType> result=al.showAndWait();
        }
    }

    public void exit(ActionEvent event) {
    }
}
