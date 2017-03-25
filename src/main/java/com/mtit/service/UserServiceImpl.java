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
    public User getUser(String body, Response res) throws SQLException {
        User user=  new Gson().fromJson(body, User.class);
         return userDataAccessLayer.login(user.getUserName(),user.getPassword());
    }

    @Override
    public User create(String body, Response res) throws SQLException{
        User user=  new Gson().fromJson(body, User.class);
        return userDataAccessLayer.register(user);
    }
}
