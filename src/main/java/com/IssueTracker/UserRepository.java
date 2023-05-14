package com.IssueTracker;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepository  {
    public List<User> users = new ArrayList<User>();

    public void save(User user) {
        users.add(user);
    }

    public User findByUsername(String username) {

        for (User user : users) {
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }
}
