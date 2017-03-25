package com.mtit.service;

import com.google.gson.Gson;
import com.mtit.dataaccess.UserDataAccessLayer;
import com.mtit.dataaccess.UserDataAccessLayerImpl;
import com.mtit.models.User;
import spark.Response;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    // access user db
    private UserDataAccessLayer userDataAccessLayer = new UserDataAccessLayerImpl();

    /**
     * Login
     * @param body
     * @param res
     * @return
     * @throws SQLException
     */
    @Override
    public User getUser(String body, Response res) throws SQLException {
        User user=  new Gson().fromJson(body, User.class);
         return userDataAccessLayer.login(user.getUserName(),user.getPassword());
    }

    /**
     * Register
     * @param body
     * @param res
     * @return
     * @throws SQLException
     */
    @Override
    public User create(String body, Response res) throws SQLException{
        User user=  new Gson().fromJson(body, User.class);
        return userDataAccessLayer.register(user);
    }
}
