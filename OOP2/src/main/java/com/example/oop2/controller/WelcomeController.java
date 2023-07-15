package com.example.oop2.controller;

import com.example.oop2.*;
import com.example.oop2.LocationAndMap.map;
import com.example.oop2.LocationAndMap.ras;
import com.example.oop2.Order.Restaurant;
import com.example.oop2.Persons.Person;
import com.example.oop2.Persons.RestaurantOwner;
import com.example.oop2.Persons.User;
import com.example.oop2.enums.Message;

public class WelcomeController {
    private static WelcomeController instance = null;
    public RestaurantOwner restaurantOwner=null;

    public WelcomeController() {

    }

    private static void setInstance(WelcomeController instance) {
        WelcomeController.instance = instance;
    }

    public static WelcomeController getInstance() {
        if (WelcomeController.instance == null) {
            WelcomeController.setInstance(new WelcomeController());
        }

        return WelcomeController.instance;
    }

    private Boolean doesUsernameExist(String username) {
        return Person.isThereAPersonWithThisUserName(username) != false;
    }

    private Boolean isAlphaNumeric(String password) {
        return password.matches("[a-zA-Z0-9]+");
    }

    private Message validatePassword(String password) {
        if (password.length() < 5)
            return Message.SHORT_PASSWORD;
        if (password.length() > 10)
            return Message.LONG_PASSWORD;
        if (!this.isAlphaNumeric(password))
            return Message.NON_ALPHA_NUMERIC_PASSWORD;

        return Message.SUCCESS;

    }

    public String handleLogin(String username, String password) {
        Person person = Person.login(username,password);

        if (person != null ) {
            return String.valueOf(person.getRole());
        } else if (doesUsernameExist(username)) {
            return "wp";
        }
        return "password or username is incorrect";

    }

    public Message handleCreateUser(String username, String password,String loc) {
        if (this.doesUsernameExist(username)) {
            return Message.USER_EXIST;
        }
        Message message;
        if ((message = this.validatePassword(password)) != Message.SUCCESS) {
            return message;
        }
        new User(username, password,get_loc(loc));
        return Message.SUCCESS;
    }
    ras get_loc(String s){
        map m=new map();
        return m.get_location(Integer.parseInt(s));
    }
    public Message handleCreateAdmin(String username, String password) {
        if (this.doesUsernameExist(username)) {
            return Message.USER_EXIST;
        }
        Message message;
        if ((message = this.validatePassword(password)) != Message.SUCCESS) {
            return message;
        }
        restaurantOwner=new RestaurantOwner(username, password);
        return Message.SUCCESS;
    }
    public Message handleCreateDelivery(String username, String password) {
        if (this.doesUsernameExist(username)) {
            return Message.USER_EXIST;
        }
        Message message;
        if ((message = this.validatePassword(password)) != Message.SUCCESS) {
            return message;
        }
        //new Deliver(username, password);
        return Message.SUCCESS;
    }

    public Message handlerestore(String username, String id,String pass) {
        Person person = Person.getPerson(Integer.parseInt(id));

        if (person != null ) {
            if(person.getName().equals(username)) {
                person.setPassword(pass);
                return Message.SUCCESS;
            }
        }

        return Message.WRONG_CREDENTIALS;
    }

    public Message handleCreateRestaurant(String username, int id, String type, String parseInt) {
        restaurantOwner=RestaurantOwnerController.using;
        Restaurant restaurant=new Restaurant(username,id,type,get_loc(parseInt));
        restaurantOwner.ownedRestaurants.add(restaurant);
        return Message.SUCCESS;
    }
}
