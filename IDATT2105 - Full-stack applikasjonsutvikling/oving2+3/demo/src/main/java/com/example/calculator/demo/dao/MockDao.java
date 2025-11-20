package com.example.calculator.demo.dao;

import java.util.ArrayList;
import java.util.List;

import com.example.calculator.demo.model.User;

public class MockDao {
    
    private static final List<User> users = new ArrayList<User>() {{
        add(new User("user1", "password1"));
        add(new User("user2", "password2"));
        add(new User("user3", "password3"));
    }};

    public static boolean checkUserCredentials(final String username, final String password) {
        for(User user : users){
            if(user.getUsername().equals(username) && user.getPassword().equals(password))  {
                return true;
            }
        }
        return false;
    }

    public static User getUser(final String userId){
        for(User user : users){
            if(user.getUsername().equals(userId)){
                return user;
            }
        }
        return null;
    }

}
