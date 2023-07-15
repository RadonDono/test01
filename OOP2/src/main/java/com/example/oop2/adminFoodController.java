package com.example.oop2;

import com.example.oop2.Order.Food;
import com.example.oop2.Order.Restaurant;
import com.example.oop2.Persons.RestaurantOwner;
import com.example.oop2.controller.RestaurantOwnerController;
import com.example.oop2.controller.WelcomeController;
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

public class adminFoodController implements Initializable
{
    RestaurantOwnerController restaurantOwnerController=new RestaurantOwnerController(RestaurantOwnerController.using);
    @FXML
    TextField IDActive;
    @FXML
    ToggleButton activate;
    @FXML
    TextField nametext;
    @FXML
    TextField pricetext;
    @FXML
    TextField edittext;
    @FXML
    ChoiceBox<String> chose;
    @FXML
    Button editbutton;

    @FXML
    Button addbutton;
    @FXML
    private TextField timeT;
    @FXML
    TextField valutext;
    @FXML
    private Label timeL;

    private Stage stage;
    private Scene scene;
    private Parent root;
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
    public void activeAction()
    {
        if(Food.getFood(Integer.parseInt(IDActive.getText()))!=null)
        {
            int foodID=(Integer.parseInt(IDActive.getText()));
            activate.setDisable(false);
            if(Food.getFood(foodID).isFoodActive())
            {
                activate.setSelected(false);
                activate.setText("deactive");
                this.restaurantOwnerController.deactive(String.valueOf(foodID));

            }
            else
            {
                activate.setSelected(true);
                activate.setText("active");
                this.restaurantOwnerController.activefood(foodID);
            }
        }
        else
        {
            activate.setDisable(true);
        }
    }

    public void activation()
    {
        int foodID=(Integer.parseInt(IDActive.getText()));
        if(activate.isSelected())
        {

        }
        else
        {

        }

    }

    public void add_action(ActionEvent event) throws IOException {
        String name=nametext.getText();
        String price=pricetext.getText();
        if (name!=null&&price!=null) {
            restaurantOwnerController.addfood(name,price);
            showadminMenu(event,RestaurantOwnerController.using);
        }else {
            Alert al=new Alert(Alert.AlertType.CONFIRMATION);
            al.setTitle("Eror!");
            al.setContentText("please enter every information");
            Optional<ButtonType> result=al.showAndWait();
        }
    }
    public void timeAction()
    {
        if(chose.getValue().equals("discount"))
        {
            timeL.setDisable(false);
            timeT.setDisable(false);
        }
        else
        {
            timeL.setDisable(!false);
            timeT.setDisable(!false);
        }
    }

    public void edit(ActionEvent event) throws IOException {
        String id=edittext.getText();
        String job=chose.getValue();
        if (id!=null&&job!=null) {
            switch (job){
                case "name":
                    this.restaurantOwnerController.editfoodname(valutext.getText(),Integer.parseInt(id));
                    break;
                case "price":
                    this.restaurantOwnerController.editfoodprice(Integer.parseInt(valutext.getText()),Integer.parseInt(id));
                case "discount":
                    if (timeT.getText()!=null) {
                        this.restaurantOwnerController.setdiscount(Integer.parseInt(id),Integer.parseInt(valutext.getText()),Integer.parseInt(timeT.getText()));
                    }

            }
            showadminMenu(event,RestaurantOwnerController.using);
        }else {
            Alert al=new Alert(Alert.AlertType.CONFIRMATION);
            al.setTitle("Eror!");
            al.setContentText("please enter every information");
            Optional<ButtonType> result=al.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] choice={"name","price","discount"};
        chose.getItems().addAll(choice);
    }

    public void exit_action(ActionEvent event) throws IOException {
        showadminMenu(event,RestaurantOwnerController.using);
    }
}
