package com.example.oop2;

import com.example.oop2.controller.WelcomeController;
import com.example.oop2.enums.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class registerController implements Initializable
{
    WelcomeController welcomeController=new WelcomeController();
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private TextField location;
    private String[] roles={"Admin","customer"};
    @FXML
    Button registerbutton;
    @FXML
    TextField usertextfield;
    @FXML
    TextField passwordtextfield;

    @FXML
    public Exception welcomeMenu(ActionEvent event) throws IOException {
        root= FXMLLoader.load(getClass().getResource("welcomeMenu.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
        return null;
    }
    public void loginMenu(ActionEvent event) throws IOException {
        root=FXMLLoader.load(getClass().getResource("loginMenu.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().addAll(roles);
    }

    public void action(ActionEvent event)
    {
        if(choiceBox.getValue().equals("customer"))
        {
            location.setDisable(false);
        }
        else
        {
            location.setDisable(true);
        }
    }

    public void register(ActionEvent actionEvent) throws IOException {
        String username=usertextfield.getText();
        String password=passwordtextfield.getText();
        if (choiceBox.getValue().equals("Admin")){
            if(username!=null&&password!=null) {
                Message message=welcomeController.handleCreateAdmin(username, password);
                if (!message.equals(Message.SUCCESS)){
                    Alert al=new Alert(Alert.AlertType.CONFIRMATION);
                    al.setTitle("Eror!");
                    al.setContentText(message.toString());
                    Optional<ButtonType> result=al.showAndWait();
                }
                Exception e=welcomeMenu(actionEvent);
            }

        } else if (choiceBox.getValue().equals("customer")) {
            String s=location.getText();
            if(username!=null&&password!=null) {
                Message message=welcomeController.handleCreateUser(username,password,s);
                if (!message.equals(Message.SUCCESS)){
                    Alert al=new Alert(Alert.AlertType.CONFIRMATION);
                    al.setTitle("Eror!");
                    al.setContentText(message.toString());
                    Optional<ButtonType> result=al.showAndWait();
                }
                Exception e = welcomeMenu(actionEvent);
            }
        }
        else{
            Alert al=new Alert(Alert.AlertType.CONFIRMATION);
            al.setTitle("Eror!");
            al.setContentText("please chose correct role");
            Optional<ButtonType> result=al.showAndWait();
        }
    }
}
