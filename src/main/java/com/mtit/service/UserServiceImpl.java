package com.mtit.service;


import com.google.gson.Gson;
import com.mtit.dataaccess.UserDataAccessLayer;
import com.mtit.dataaccess.UserDataAccessLayerImpl;
import com.mtit.models.User;
import spark.Response;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private UserDataAccessLayer userDataAccessLayer = new UserDataAccessLayerImpl();
    @Override
    public User loginUser(String body, Response res){
        User user=  new Gson().fromJson(body, User.class);
        try {
            return userDataAccessLayer.login(user.getUserName(),user.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new User(user.getUserName(),"");
    }

    @Override
    public User registerUser(String body, Response res){
        User user=  new Gson().fromJson(body, User.class);
        try {
            return userDataAccessLayer.register(user);
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return user;
    }
}
