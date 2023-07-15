package com.example.oop2;

import com.example.oop2.controller.RestaurantOwnerController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class adminOrdersController implements Initializable
{
    RestaurantOwnerController restaurantOwnerController=new RestaurantOwnerController(RestaurantOwnerController.using);
    @FXML
    Button button;
    @FXML
    ChoiceBox<String> orderType;
    @FXML
    private Label show;
    @FXML
    private ListView<String> list;


    public void orderTypeAction()
    {
        if(orderType.getValue().equals("Received"))
        {
            button.setText("accept");
            button.setDisable(false);
            list.getItems().addAll(RestaurantOwnerController.showArr(RestaurantOwnerController.restaurant.receivedOrders));
        }
        else if(orderType.getValue().equals("In Progress"))
        {
            button.setText("Finish");
            button.setDisable(false);
            list.getItems().addAll(RestaurantOwnerController.showArr(RestaurantOwnerController.restaurant.currentOrders));
        }
        else if(orderType.getValue().equals("Finished"))
        {
            button.setDisable(true);
            list.getItems().addAll(RestaurantOwnerController.showArr(RestaurantOwnerController.restaurant.finishedOrders));
        }
    }

    public void listAction()
    {
        show.setText(list.getSelectionModel().getSelectedItem());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] temp={"Received","In Progress","Finished"};
        orderType.getItems().addAll(temp);
        list.getItems().addAll(temp);
    }
}
