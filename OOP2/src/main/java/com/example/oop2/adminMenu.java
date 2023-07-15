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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class adminMenu implements Initializable
{
    public RestaurantOwnerController restaurantOwnerController=new RestaurantOwnerController(RestaurantOwnerController.using);
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private ChoiceBox<String> restaurants;
    @FXML
    private ListView<String> foods;
    @FXML
    public void welcomeMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("welcomeMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

   public void addRestaurant(ActionEvent event) throws IOException {
        if(restaurants.getValue().equals("Add Restaurant"))
        {
            root = FXMLLoader.load(getClass().getResource("addRestaurantMenu.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else if (restaurants.getValue()!=null){
            String id=getid(restaurants.getValue());
            System.out.println(id);
            this.restaurantOwnerController.restaurant = Restaurant.getRestaurant(Integer.parseInt(id));
            System.out.println(Restaurant.getRestaurant(Integer.parseInt(id)));
            showfood();
       }
    }

    public void orders(ActionEvent event) throws IOException {
        root=FXMLLoader.load(getClass().getResource("adminOrders.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void showfood() {
        foods.getItems().removeAll();
        foods.getItems().addAll("active foods:");
        for (Food food :this.restaurantOwnerController.restaurant.activeFoods) {
            foods.getItems().addAll(food.toString());
        }
        foods.getItems().addAll("deactive foods:");
        for (Food food :this.restaurantOwnerController.restaurant.deActiveFoods) {
            foods.getItems().addAll(food.toString());
        }
    }
    public void food(ActionEvent event) throws IOException {
        if(this.restaurantOwnerController.restaurant!=null)
        {
            root=FXMLLoader.load(getClass().getResource("adminFoods.fxml"));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else
        {
            Alert al=new Alert(Alert.AlertType.CONFIRMATION);
            al.setTitle("Eror!");
            al.setContentText("Please add or select a restaurant");
            Optional<ButtonType> result=al.showAndWait();
        }

    }

    String getid(String x){
        String[] s=x.split(" ");
        return s[s.length-1];
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> restaurant=new ArrayList<>();
        ArrayList< Restaurant> temp =RestaurantOwnerController.using.ownedRestaurants;
        for(Restaurant r:temp)
        {
            restaurant.add(r.toString());
        }
        restaurant.add("Add Restaurant");
        restaurants.getItems().addAll(restaurant);
    }
}
