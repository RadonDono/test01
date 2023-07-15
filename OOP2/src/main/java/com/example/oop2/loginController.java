package com.example.oop2;

import com.example.oop2.Persons.RestaurantOwner;
import com.example.oop2.controller.RestaurantOwnerController;
import com.example.oop2.controller.WelcomeController;
import com.example.oop2.enums.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import java.util.Random;
import java.util.ResourceBundle;

public class loginController implements Initializable
{
    WelcomeController welcomeController=new WelcomeController();
    @FXML
    TextField name;

    Random random=new Random();
    int x;
    int y;
    int ans;
    @FXML
    TextField password;
    @FXML
    Label question;
    @FXML
    TextField answer;
    @FXML
    Button login;
    @FXML
    TextField passtext;
    @FXML
    TextField idtext;

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public void welcomeMenu(ActionEvent event) throws IOException {
        root= FXMLLoader.load(getClass().getResource("welcomeMenu.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void restore(ActionEvent event) throws IOException {
        root= FXMLLoader.load(getClass().getResource("restoreMenu.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void registerMenu(ActionEvent event) throws IOException
    {
        root=FXMLLoader.load(getClass().getResource("registerMenu.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void loginMenu(ActionEvent event) throws IOException {
        root=FXMLLoader.load(getClass().getResource("loginMenu.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public Exception showadminMenu(ActionEvent event,RestaurantOwner r) throws IOException {
        RestaurantOwnerController.using=r;
        root= FXMLLoader.load(getClass().getResource("adminMenu.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();

        return null;
    }
    public void loginaction(ActionEvent event) throws IOException {
        String username=name.getText();
        String pass=password.getText();
        String answ=answer.getText();
            if(username!=null&&password!=null&&answ!=null) {
                if (Integer.parseInt(answ)==ans) {
                    String message = welcomeController.handleLogin(username, pass);
                    System.out.println(message);
                    if (message.equals("RestaurantOwner")) {
                       showadminMenu(event,RestaurantOwner.getRestaurantOwner(username));

                    } else if (message.equals("User")) {
                        //UserMenu userMenu=new UserMenu(User.getUser(username));
                        //userMenu.run();
                    } else if (message.equals("Deliver")) {

                    } else if (message.equals("wp")) {
                        Alert al=new Alert(Alert.AlertType.CONFIRMATION);
                        al.setTitle("eror!");
                        al.setContentText("Wring password!"+"\n"+"Do you want to enter Id");
                        Optional<ButtonType> result=al.showAndWait();
                        if(result.isEmpty()){

                        } else if (result.get()==ButtonType.OK) {
                            restore(event);
                            String id=idtext.getText();
                            String pas=passtext.getText();
                            if(username!=null&&password!=null) {
                                Message message1=welcomeController.handlerestore(username,id,pas);
                                if (message1.equals(Message.SUCCESS)){
                                    loginMenu(event);
                                }
                            }

                        }else if (result.get()==ButtonType.CANCEL) {

                        }
                    }
                    else {
                        Alert al=new Alert(Alert.AlertType.CONFIRMATION);
                        al.setTitle("Eror!");
                        al.setContentText("wrong pass ");
                        Optional<ButtonType> result=al.showAndWait();
                    }
                }
                else {
                    Alert al=new Alert(Alert.AlertType.CONFIRMATION);
                    al.setTitle("Eror!");
                    al.setContentText("wrong answer");
                    Optional<ButtonType> result=al.showAndWait();
                }

        }
        else{
            Alert al=new Alert(Alert.AlertType.CONFIRMATION);
            al.setTitle("Eror!");
            al.setContentText("please enter every field");
            Optional<ButtonType> result=al.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        x=random.nextInt()%100;
        y=random.nextInt()%100;
        ans=x+y;
        question.setText(x+" + "+y+" =");
    }
}
