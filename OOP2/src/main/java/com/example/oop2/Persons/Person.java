package com.example.oop2.Persons;

import com.example.oop2.*;
import java.util.ArrayList;

public class Person {
     static ArrayList<Person> people=new ArrayList<>();
    protected Role role;
    protected int ID;
    protected String name;
    protected String password;
    public int getID(){return ID;}
    public void setPassword(String x){
        password=x;
    }
    public String getName(){return name;}
    public Object getRole(){return role;}
    public static Person getPerson(int personID)
    {
        for(Person person:people)
        {
            if(person.ID==personID)
            {
                return person;
            }
        }
        return null;
    }
    public static Person login(String name,String password)
    {
        for(Person person:people)
        {
            if(person.name.equals(name)&&person.password.equals(password))
            {
                return person;
            }
        }

return null;    }
    public static boolean isThereAPersonWithThisUserName(String name)
    {
        for(Person person:people)
        {
            if(person.name.equals(name))
            {
                return true;
            }
        }
        return false;
    }
}
